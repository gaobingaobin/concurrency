package com.example.concurrency.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
* @Description: 消费者 (多消费者，rabbitMq 会以循环的方式均匀的分配给每个消费者)
* @author gaobin
* @createDate 2019/1/16 15:55
*/
@Component
public class ThreeConsumer {

    @RabbitListener(queues = {"first-queue"})
    public void handleMessage(String message) throws Exception {
        // 处理消息
        System.out.println("ThreeConsumer {} handleMessage :"+message);
    }
}
