package com.yxz.wulibibiji.dto;

import com.yxz.wulibibiji.entity.Message;

import java.util.HashMap;

/**
 * @author yangxiaozhuo
 * @date 2023/01/23
 */
public class MessageDTO extends Message {
    private HashMap<String, String> contentMap;

    public HashMap<String, String> getContentMap() {
        return contentMap;
    }

    public void setContentMap(HashMap<String, String> contentMap) {
        this.contentMap = contentMap;
    }

    public MessageDTO() {
    }

    public MessageDTO(Message message) {
        super(message.getId(), message.getFromId(), message.getToId(), message.getConversionId(), message.getContent(), message.getStatus(), message.getCreatedTime());
    }
}
