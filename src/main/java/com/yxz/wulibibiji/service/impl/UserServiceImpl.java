package com.yxz.wulibibiji.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.wulibibiji.dto.LoginFormDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.dto.UserDTO;
import com.yxz.wulibibiji.entity.User;
import com.yxz.wulibibiji.mapper.UserMapper;
import com.yxz.wulibibiji.service.UserService;
import com.yxz.wulibibiji.service.other.IQiNiuService;
import com.yxz.wulibibiji.utils.MailClient;
import com.yxz.wulibibiji.utils.MyFileUtil;
import com.yxz.wulibibiji.utils.RedisConstants;
import com.yxz.wulibibiji.utils.UserHolder;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.yxz.wulibibiji.utils.RedisConstants.*;
import static com.yxz.wulibibiji.utils.SystemConstants.*;

/**
 * @author Yang
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2022-11-18 22:24:23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private IQiNiuService qiNiuService;

    @Autowired
    private HttpServletRequest request;

    //发送验证码
    @Override
    @DS("slave")
    public Result sentCode(String email) {
        //检查邮箱格式是否正确  email == null ||
        if (!ckeckEmail(email)) {
            return Result.fail("邮箱格式不正确,请检查后再次填写");
        }
        //检查用户是否已经存在
        if (this.getById(email) != null) {
            return Result.fail("此用户已经存在");
        }
        //发email验证码
        return sentEmail(email);
    }

    @Override
    public Result create(LoginFormDTO loginFormDTO) {
        if (!checkPassword(loginFormDTO.getPassword())) {
            return Result.fail("密码不符合要求");
        }
        String key = CREATE_CODE_KEY + loginFormDTO.getEmail();
        String redisCode = (String) redissonClient.getBucket(key).get();
        if (redisCode == null || !redisCode.equals(loginFormDTO.getCode())) {
            return Result.fail("邮箱或验证码错误，请重试");
        }
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        // 5393554e94bf0eb6436f240a4fd71282
        String passwordMd5 = md5.digestHex(loginFormDTO.getPassword());
        User user = new User(loginFormDTO.getEmail(),
                USER_NICK_NAME_PREFIX + loginFormDTO.getEmail().substring(0, 6),
                loginFormDTO.getPassword(), passwordMd5, IMAGE_UPLOAD_DIR + DEFAULT_AVATAR, DateUtil.date(), 1);
        save(user);
        return Result.ok();
    }

    @Override
    @DS("slave")
    public Result login(LoginFormDTO loginForm) {
        //1.验证邮箱 2.验证账号密码 3.不存在或不一致 报错
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();
        User user = query().eq("user_id", email).one();
        if (user == null) {
            return Result.fail("该用户未注册");
        }
        if (!user.getPassword().equals(password)) {
            return Result.fail("账号或用户名错误,请核实后再试!");
        }
        //单点登录 email - token
        String single = SINGLE_POINT_KEY + loginForm.getEmail();
        String s = (String) redissonClient.getBucket(single).get();
        if (!StrUtil.isBlank(s)) {
            redissonClient.getBucket(LOGIN_USER_KEY + s).delete();
        }
        String token = UUID.randomUUID().toString(true);
        redissonClient.getBucket(single).set(token, LOGIN_USER_TTL, TimeUnit.HOURS);
        String key = LOGIN_USER_KEY + token;
        Map<String, Object> map = new HashMap<>();
        map.put("email", user.getUserId());
        map.put("nickName", user.getNickname());
        map.put("avatar", IMAGE_UPLOAD_DIR + user.getAvatar());
        map.put("sex", user.getSex().toString());
        RMap<Object, Object> temp = redissonClient.getMap(key);
        temp.expire(LOGIN_USER_TTL, TimeUnit.HOURS);
        temp.putAll(map);
        return Result.ok(token);
    }
    @Override
    @DS("slave")
    public Result loginPlus(LoginFormDTO loginForm) {
        //1.验证邮箱 2.验证账号密码 3.不存在或不一致 报错
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();
        User user = query().eq("user_id", email).one();
        if (user == null) {
            return Result.fail("该用户未注册");
        }
        if (!user.getPassword().equals(password)) {
            return Result.fail("账号或用户名错误,请核实后再试!");
        }
        //单点登录 email - token
        String single = SINGLE_POINT_KEY + loginForm.getEmail();
        String s = (String) redissonClient.getBucket(single).get();
        if (!StrUtil.isBlank(s)) {
            redissonClient.getBucket(LOGIN_USER_KEY + s).delete();
        }
        String token = UUID.randomUUID().toString(true);
        redissonClient.getBucket(single).set(token, LOGIN_USER_TTL, TimeUnit.HOURS);
        String key = LOGIN_USER_KEY + token;
        Map<String, Object> map = new HashMap<>();
        map.put("email", user.getUserId());
        map.put("nickName", user.getNickname());
        map.put("avatar", user.getAvatar());
        map.put("sex", user.getSex().toString());
        RMap<Object, Object> temp = redissonClient.getMap(key);
        temp.expire(LOGIN_USER_TTL, TimeUnit.HOURS);
        temp.putAll(map);
        map.put("token", token);
        return Result.ok(map);
    }

    @Override
    public Result edit(User user) {
        UserDTO userDTO = UserHolder.getUser();
        String nickname = user.getNickname();
        Integer sex = user.getSex();
        if (StrUtil.isBlank(nickname) && (sex == null || sex.equals(userDTO.getSex()))) {
            return Result.fail("上传的信息为空!");
        }
        if (sex != null && sex != 1 && sex != 0) {
            return Result.fail("性别不存在");
        }
        if (StrUtil.isBlank(nickname)) {
            user.setNickname(userDTO.getNickName());
        }
        user.setUserId(userDTO.getEmail());
        boolean update = this.updateById(user);
        if (update) {
            return Result.ok();
        } else {
            return Result.fail("服务器错误,上传失败!");
        }
    }

    @Override
    @Transactional
    public Result uploadAvatar(MultipartFile file, HttpServletRequest request) {
        if (!MyFileUtil.isImg(file)) {
            return Result.fail("只支持jpg、png、webp、jpeg四种图片格式");
        }
        if (!MyFileUtil.sizeCheck(file, 1)) {
            return Result.fail("只支持上传大小为1MB以内的图片");
        }
        try {
            UserDTO userDTO = UserHolder.getUser();
            String format = DateUtil.format(DateUtil.date(), "yyyy/MM/");
            String type = FileTypeUtil.getType(file.getInputStream());
            String string = RandomUtil.randomString(10) + "." + type;
            String key = "avatar/" + format + string;
            Result result = qiNiuService.uploadFile(file.getInputStream(), key);
            if (result.getCode() == 200) {
                //更新数据库
                User user = getById(userDTO.getEmail());
                String oldAvatar = user.getAvatar();
                String url = IMAGE_UPLOAD_DIR + key;
                user.setAvatar(url);
                updateById(user);
                //更新redis
                String token = RedisConstants.LOGIN_USER_KEY + request.getHeader("authorization");
                redissonClient.getMap(token).put("avatar", url);
                //删除旧头像
                if (!oldAvatar.equals(IMAGE_UPLOAD_DIR + DEFAULT_AVATAR)) {
                    String oldKey = oldAvatar.substring(IMAGE_UPLOAD_DIR.length());
                    qiNiuService.delete(oldKey);
                }
                return Result.ok("更新成功");
            }
            return Result.fail("更新失败！");
        } catch (Exception e) {
            return Result.fail("系统异常！");
        }
    }

    @Override
    public Result editPassword(String oldPassword, String newPassword) {
        String email = UserHolder.getUser().getEmail();
        User user = getById(email);
        if (!user.getPassword().equals(oldPassword)) {
            return Result.fail("原密码不正确");
        }
        if (!checkPassword(newPassword)) {
            return Result.fail("密码不符合要求");
        }
        user.setPassword(newPassword);
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String passwordMd5 = md5.digestHex(newPassword);
        user.setSalt(passwordMd5);
        boolean flag = this.updateById(user);
        if (flag) {
            return Result.ok("修改密码成功");
        } else {
            return Result.fail("服务器错误，请稍后再试");
        }
    }

    @Override
    @DS("slave")
    public Result queryUserInfo(String userId) {
        User user = query().eq("user_id", userId).one();
        if (user == null) {
            return Result.fail("没有此用户");
        }
        UserDTO userDTO = new UserDTO(user.getUserId(), user.getNickname(), user.getAvatar(), user.getSex());
        return Result.ok(userDTO);
    }

    @Override
    public Result logout() {
        UserDTO user = UserHolder.getUser();
        if (user == null) {
            return Result.fail("请先登录");
        }
        //1。获取token
        String token = request.getHeader("authorization");
        String key = RedisConstants.LOGIN_USER_KEY + token;
        redissonClient.getBucket(key).delete();
        String single = SINGLE_POINT_KEY + user.getEmail();
        redissonClient.getBucket(single).delete();
        return Result.ok("退出登录成功");

    }

    @Override
    public Result isLogin() {
        return Result.ok(UserHolder.getUser() != null);
    }

    private Result sentEmail(String email) {
        //发email验证码
        Context context = new Context();
        //生成位code字符串
        String code = RandomUtil.randomNumbers(6);
        String key = CREATE_CODE_KEY + email;
        //redis新增缓存
        redissonClient.getBucket(key).set(code, CREATECODE_TTL, TimeUnit.MINUTES);
        context.setVariable("code", code);
        String content = templateEngine.process("mail/mail", context);
        try {
            mailClient.sendMail(email, "欢迎加入武理哔哔机", content);
        } catch (Exception e) {
            return Result.fail("发送邮件失败");
        }
        return Result.ok();
    }

    /**
     * 校验密码是否符合要求
     *
     * @param password
     * @return
     */
    private boolean checkPassword(String password) {
        if (password.length() < 6) {
            return false;
        }
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (c >= '0' && c <= '9') {

            } else if (c >= 'a' && c <= 'z') {

            } else if (c >= 'A' && c <= 'Z') {

            } else {
                return false;
            }
        }
        return true;
    }


    /**
     * 检查是否是武理教育邮箱
     *
     * @param email
     * @return
     */
    private boolean ckeckEmail(String email) {
        return Validator.isEmail(email);
//        return email.length() == 18 && "@whut.edu.cn".equals(email.substring(6));
    }
}




