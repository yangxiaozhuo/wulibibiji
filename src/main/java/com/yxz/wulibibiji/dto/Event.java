package com.yxz.wulibibiji.dto;

/**
 * @author yangxiaozhuo
 * @date 2023/01/23
 */
public class Event {
    private static final long serialVersionUID = 1L;

    private String routingKey;
    private String userId;
    private String entityType;
    private String entityId;
    private String entityUserId;

    public Event() {
    }

    public Event(String routingKey, String userId, String entityType, String entityId, String entityUserId) {
        this.routingKey = routingKey;
        this.userId = userId;
        this.entityType = entityType;
        this.entityId = entityId;
        this.entityUserId = entityUserId;
    }

    @Override
    public String toString() {
        return "Event{" +
                "routingKey='" + routingKey + '\'' +
                ", userId=" + userId +
                ", entityType=" + entityType +
                ", entityId=" + entityId +
                ", entityUserId=" + entityUserId +
                '}';
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityUserId() {
        return entityUserId;
    }

    public void setEntityUserId(String entityUserId) {
        this.entityUserId = entityUserId;
    }
}
