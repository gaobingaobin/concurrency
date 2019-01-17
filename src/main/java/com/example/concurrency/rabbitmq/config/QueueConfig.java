package com.example.concurrency.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;
/**
* @Description:
* @author gaobin
* @createDate 2019/1/16 15:11
*/
@Configuration
public class QueueConfig {
    /**
     durable="true" 持久化 rabbitmq重启的时候不需要创建新的队列
     auto-delete 表示消息队列没有在使用时将被自动删除 默认是false
     exclusive  表示该消息队列是否只在当前connection生效,默认是false
     */
    public Queue firstQueue(){
        return new Queue("first-queue",true,false,false);
    }
    public Queue secondQueue(){
        return new Queue("second-queue",true,false,false);
    }

}
