package com.nowcoder.community.event;

/**
 * @Author: pyhita
 * @Date: 2022/2/28
 * @Descrption: com.nowcoder.community.event
 * @Version: 1.0
 */

import com.alibaba.fastjson.JSONObject;
import com.nowcoder.community.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 将消息发送到指定的topic
 */
@Component
public class EventProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void fireEvent(Event event) {
        kafkaTemplate.send(event.getTopic(), JSONObject.toJSONString(event));
    }
}
