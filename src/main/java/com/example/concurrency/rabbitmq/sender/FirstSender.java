package com.example.concurrency.rabbitmq.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.concurrency.rabbitmq.config.RabbitMqConfig;

/**
* @Description: 生产者
* @author gaobin
* @createDate 2019/1/16 16:07
*/

@Component
public class FirstSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 发送消息
     * @param uuid
     * @param message  消息
     */
    public void send1(String uuid,Object message) {
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, "queue_one_key1",
                message, correlationId);
    }
    public void send2(String uuid,Object message) {
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, "queue_one_key2",
                message, correlationId);
    }

}
