package com.yxz.wulibibiji.Event;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.yxz.wulibibiji.dto.Event;
import com.yxz.wulibibiji.entity.Message;
import com.yxz.wulibibiji.mapper.MessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.yxz.wulibibiji.utils.RabbitConstants.*;

/**
 * @author yangxiaozhuo
 * @date 2023/01/23
 */
@Slf4j
@Component
public class EventConsumer {

    @Autowired
    private MessageMapper messageMapper;

    @RabbitListener(queues = {TOPIC_FOLLOW, TOPIC_LIKE, TOPIC_COMMENT})
    public void handleCommentMessage(String msg, org.springframework.amqp.core.Message m, Channel channel) throws Exception {
        try {
            if (StrUtil.isBlank(msg)) {
                log.error("消息的内容为空");
                return;
            }
            Event event = JSONUtil.toBean(msg, Event.class);
            if (event == null) {
                log.error("消息格式错误!");
            }
            // 发送站内通知
            Message message = new Message();
            message.setFromId(SYSTEM_USER_ID);
            message.setToId(event.getEntityUserId());
            message.setConversionId(event.getRoutingKey());
            message.setCreatedTime(DateUtil.date());
            message.setStatus(0);
            Map<String, Object> content = new HashMap<>();
            content.put("userId", event.getUserId());
            content.put("entityType", event.getEntityType());
            content.put("entityId", event.getEntityId());
            message.setContent(JSONUtil.toJsonStr(content));
            messageMapper.insert(message);
        } catch (Exception e) {
            log.error("异常!");
            return;
        }
    }
}
