package com.yxz.wulibibiji.mq;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.yxz.wulibibiji.dto.Event;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.yxz.wulibibiji.utils.RabbitConstants.*;

/**
 * @author yangxiaozhuo
 * @date 2023/01/23
 */
@SpringBootTest
public class RabbitMq {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    void contextLoads() {
        for (int i = 0; i < 10; i++) {
            Event event = new Event();
            event.setRoutingKey(TOPIC_COMMENT);
            // 将事件发布到指定的主题(评论、点赞、关注)
            rabbitTemplate.convertAndSend(event.getRoutingKey(), JSONUtil.toJsonStr(event));
        }
    }

    @Test
    @RabbitListener(queues = {TOPIC_COMMENT})
    void handleCommentMessage(String msg, org.springframework.amqp.core.Message m, Channel channel){
        System.out.println(msg);
    }
}
