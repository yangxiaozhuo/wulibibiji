package com.yxz.wulibibiji.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.wulibibiji.dto.LoginFormDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.User;
import com.yxz.wulibibiji.mapper.UserMapper;
import com.yxz.wulibibiji.service.UserService;
import com.yxz.wulibibiji.utils.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.yxz.wulibibiji.utils.RedisConstants.*;
import static com.yxz.wulibibiji.utils.SystemConstants.USER_NICK_NAME_PREFIX;

/**
 * @author Yang
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2022-11-18 22:24:23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    //发送验证码
    @Override
    public Result sentCode(String email) {
        //检查邮箱格式是否正确
        if (email == null || !ckeckEmail(email)) {
            return Result.fail("邮箱格式不正确,请检查后再次填写");
        }
        //检查用户是否已经存在
        if (this.getById(email) != null) {
            return Result.fail("此用户已经存在");
        }

        Context context = new Context();
        //生成位code字符串
        String code = RandomUtil.randomNumbers(6);
        String key = CREATE_CODE_KEY + email;
        //redis新增缓存
        stringRedisTemplate.opsForValue().set(key, code, CREATECODE_TTL, TimeUnit.MINUTES);

        context.setVariable("code", code);
        String content = templateEngine.process("mail/mail", context);
        System.out.println(content);
        try {
            mailClient.sendMail(email, "欢迎加入武理哔哔机", content);
        } catch (Exception e) {
            return Result.fail("发送邮件失败");
        }
        return Result.ok();
    }

    @Override
    public Result create(LoginFormDTO loginFormDTO) {
        if (!checkPassword(loginFormDTO.getPassword())) {
            return Result.fail("密码不符合要求");
        }
        String key = CREATE_CODE_KEY + loginFormDTO.getEmail();
        String redisCode = stringRedisTemplate.opsForValue().get(key);
        if (redisCode == null || !redisCode.equals(loginFormDTO.getCode())) {
            return Result.fail("邮箱或验证码错误，请重试");
        }
        Digester md5 = new Digester(DigestAlgorithm.MD5);

        // 5393554e94bf0eb6436f240a4fd71282
        String passwordMd5 = md5.digestHex(loginFormDTO.getPassword());
        User user = new User(loginFormDTO.getEmail(),
                USER_NICK_NAME_PREFIX + loginFormDTO.getEmail().substring(0, 6),
                loginFormDTO.getPassword(), passwordMd5, null, DateUtil.date(), 1);
        save(user);
        return Result.ok();
    }

    @Override
    public Result login(LoginFormDTO loginForm, HttpSession session) {
        //1.验证邮箱 2.验证账号密码 3.不存在或不一致 报错
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();
        User user = query().eq("user_id", email).eq("password", password).one();
        if (user == null) {
            return Result.fail("该用户未注册");
        }
        String token = UUID.randomUUID().toString(true);
        String key = LOGIN_USER_KEY + token;
        Map<String, Object> map = new HashMap<>();
        map.put("email", user.getUserId());
        map.put("nickName", user.getNickname());
        map.put("avatar", user.getAvatar());
        map.put("sex", user.getSex().toString());
        stringRedisTemplate.opsForHash().putAll(key, map);
        stringRedisTemplate.expire(key, LOGIN_USER_TTL, TimeUnit.HOURS);
        session.setAttribute("authorization",token);
        return Result.ok();
    }

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
        return email.length() == 18 && "@whut.edu.cn".equals(email.substring(6));
    }
}




