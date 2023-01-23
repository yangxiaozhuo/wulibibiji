package com.yxz.wulibibiji.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.dfa.SensitiveUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.wulibibiji.dto.MessageDTO;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.dto.UserDTO;
import com.yxz.wulibibiji.entity.Message;
import com.yxz.wulibibiji.entity.User;
import com.yxz.wulibibiji.mapper.MessageMapper;
import com.yxz.wulibibiji.service.MessageService;
import com.yxz.wulibibiji.service.UserService;
import com.yxz.wulibibiji.utils.SystemConstants;
import com.yxz.wulibibiji.utils.UserHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Yang
 * @description 针对表【message】的数据库操作Service实现
 * @createDate 2023-01-13 22:44:47
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private UserService userService;

    @Override
    public Result sendMessage(String toUserId, String content) {
        String fromId = UserHolder.getUser().getEmail();
        String conversionId = "";
        if (fromId.compareTo(toUserId) < 0) {
            conversionId = fromId + "->" + toUserId;
        } else {
            conversionId = toUserId + "->" + fromId;
        }
        content = SensitiveUtil.sensitiveFilter(content);
        Message message = new Message(null, fromId, toUserId, conversionId, content, 0, DateUtil.date());
        if (save(message)) {
            return Result.ok(message);
        } else {
            return Result.fail("发送失败");
        }
    }

    @Override
    public Result getFirstList(Integer current) {
        String email = UserHolder.getUser().getEmail();
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.ne("status", "2").ne("from_id", 1).and(i -> i.eq("from_id", email).or().eq("to_id", email));
        List<Message> messages = messageMapper.selectListPage((current - 1) * SystemConstants.MAX_PAGE_SIZE, SystemConstants.MAX_PAGE_SIZE, wrapper);
        return Result.ok(messages);
    }

    @Override
    public Result getUnreadCount(String userId) {
        String fromId = UserHolder.getUser().getEmail();
        String conversionId = "";
        if (fromId.compareTo(userId) < 0) {
            conversionId = fromId + "->" + userId;
        } else {
            conversionId = userId + "->" + fromId;
        }
        Integer count = query().eq("conversion_id", conversionId).eq("from_id", userId).eq("status", 0).count();
        return Result.ok(count);
    }


    @Override
    public Result withdrawMessage(Integer id) {
        Date date = DateUtil.date();
        Message message = getById(id);
        if (message == null) {
            return Result.fail("没有此消息");
        }
        if (message.getStatus() == 2) {
            return Result.fail("此消息已撤回");
        }
        if (!message.getFromId().equals(UserHolder.getUser().getEmail())) {
            return Result.fail("不允许撤回他人的消息");
        }
        Date createdTime = message.getCreatedTime();
        Date offset = DateUtil.offset(createdTime, DateField.SECOND, 120);
        if (DateUtil.isIn(date, createdTime, offset)) {
            message.setStatus(2);
            updateById(message);
            return Result.ok("撤回成功");
        } else {
            return Result.fail("超过两分钟不允许撤回");
        }
    }

    @Override
    public Result unreadAllMessage() {
        UserDTO user = UserHolder.getUser();
        if (user == null) {
            return Result.ok(0);
        }
        Integer count = query().eq("to_id", user.getEmail()).eq("status", 0).count();
        return Result.ok(count);
    }

    @Override
    public Result unreadPrivateMessage() {
        UserDTO user = UserHolder.getUser();
        if (user == null) {
            return Result.ok(0);
        }
        Integer count = query().eq("to_id", user.getEmail()).eq("status", 0).ne("from_id", "1").count();
        return Result.ok(count);
    }

    @Override
    public Result unreadSystemMessage() {
        UserDTO user = UserHolder.getUser();
        if (user == null) {
            return Result.ok(0);
        }
        Integer count = query().eq("to_id", user.getEmail()).eq("status", 0).eq("from_id", "1").count();
        return Result.ok(count);
    }

    @Override
    public Result getLatestNotice(String topic) {
        if (UserHolder.getUser() == null) {
            return Result.ok(0);
        }
        Message message = messageMapper.selectLatestNotice(UserHolder.getUser().getEmail(), topic);
        MessageDTO messageDTO = new MessageDTO(message);
        messageDTO.setContentMap(JSONUtil.toBean(message.getContent(), HashMap.class));
        return Result.ok(messageDTO);
    }

    @Override
    public Result getAllNotice(String topic,Integer current) {
        if (UserHolder.getUser() == null) {
            return Result.ok(0);
        }

        Page<Message> page = query().ne("status", "2").eq("from_id", "1").eq("to_id", UserHolder.getUser().getEmail())
                .eq("conversion_id", topic).orderByDesc("created_time").page(new Page<>(current, SystemConstants.MAX_PAGE_SIZE));
        update().ne("status", "2").eq("from_id", "1").eq("to_id", UserHolder.getUser().getEmail())
                .eq("conversion_id", topic).set("status", "1").update();
        return Result.ok(page);
    }

    @Override
    public Result getNoticeUnreadCount(String topic) {
        if (UserHolder.getUser() == null) {
            return Result.ok(0);
        }
        Integer count = query().eq("status", "0").eq("from_id", "1")
                .eq("to_id", UserHolder.getUser().getEmail()).eq("conversion_id", topic).count();
        return Result.ok(count);
    }

    @Override
    public Result getMessage(String toUserId, Integer current) {
        String myId = UserHolder.getUser().getEmail();
        String conversionId = "";
        if (myId.compareTo(toUserId) < 0) {
            conversionId = myId + "->" + toUserId;
        } else {
            conversionId = toUserId + "->" + myId;
        }
        update().eq("conversion_id", conversionId).eq("to_id", myId).eq("status", 0).set("status", 1).update();
        Page<Message> res = query().eq("conversion_id", conversionId).orderByDesc("created_time").page(new Page<>(current, 5));
        User toUser = userService.getById(toUserId);
        UserDTO myUser = UserHolder.getUser();
        res.getRecords().forEach(message -> {
            this.addOtherUser(message, toUser, myUser);
        });
        return Result.ok(res);
    }

    private void addOtherUser(Message message, User toUser, UserDTO myUser) {
        if (message.getFromId().equals(myUser.getEmail())) {
            //消息是我发送的
            message.setFromAvatar(myUser.getAvatar());
            message.setFromNickname(myUser.getNickName());
            message.setToAvatar(toUser.getAvatar());
            message.setToNickname(toUser.getNickname());
        } else {
            message.setFromAvatar(toUser.getAvatar());
            message.setFromNickname(toUser.getNickname());
            message.setToAvatar(myUser.getAvatar());
            message.setToNickname(myUser.getNickName());
        }
    }
}