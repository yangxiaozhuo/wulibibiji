package com.yxz.wulibibiji.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.wulibibiji.dto.Result;
import com.yxz.wulibibiji.entity.Message;

/**
 * @author Yang
 * @description 针对表【message】的数据库操作Service
 * @createDate 2023-01-14 00:18:19
 */
public interface MessageService extends IService<Message> {

    Result getMessage(String userId, Integer current);

    Result unreadAllMessage();

    Result withdrawMessage(Integer id);

    Result sendMessage(String toId, String content);

    Result getFirstList(Integer current);

    Result getNoticeList();

    Result getUnreadCount(String userId);

    Result unreadPrivateMessage();

    Result unreadSystemMessage();

    Result getLatestNotice(String topic);

    Result getAllNotice(String topic, Integer current);

    Result getNoticeUnreadCount(String topic);
}
