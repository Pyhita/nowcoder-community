package com.nowcoder.community.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: pyhita
 * @Date: 2022/2/28
 * @Descrption: com.nowcoder.community.entity
 * @Version: 1.0
 */
public class Event {

    int userid;
    int entityId;
    int entityType;
    String topic;
    int entityUserId;
    Map<String, Object> data = new HashMap<>();

    public Map<String, Object> getData() {
        return data;
    }

    public Event setData(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public int getUserid() {
        return userid;
    }

    public Event setUserid(int userid) {
        this.userid = userid;
        return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public Event setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public Event setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public Event setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public int getEntityUserId() {
        return entityUserId;
    }

    public Event setEntityUserId(int entityUserId) {
        this.entityUserId = entityUserId;
        return this;
    }
}

