package com.example.concurrency.rabbitmq.mqcallbck;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
/**
* @Description:
* @author gaobin
* @createDate 2019/1/16 18:00
*/
public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {
    // 通过实现 ConfirmCallback 接口，消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("MsgSendConfirmCallBack  , 回调id:" + correlationData);
        if (ack) {
            System.out.println("消息推送成功");
        } else {
            System.out.println("消息推送失败:" + cause+"\n重新发送");
        }
    }
}
