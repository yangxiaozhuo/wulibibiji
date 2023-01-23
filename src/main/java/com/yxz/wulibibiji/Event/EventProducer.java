package com.yxz.wulibibiji.Event;

import cn.hutool.json.JSONUtil;
import com.yxz.wulibibiji.dto.Event;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yangxiaozhuo
 * @date 2023/01/23
 */
@Component
public class EventProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 处理事件
    public void fireEvent(Event event) {
        // 将事件发布到指定的主题(评论、点赞、关注)
        rabbitTemplate.convertAndSend(event.getRoutingKey(), JSONUtil.toJsonStr(event));
    }

}
