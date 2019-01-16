package rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
/**
* @Description: 消费者
* @author gaobin
* @createDate 2019/1/16 15:55
*/
@Component
public class FirstConsumer {
    @RabbitListener(queues = {"first-queue"})
    public void handleMessage(String message) throws Exception {
        // 处理消息
        System.out.println("FirstConsumer {} handleMessage :"+message);
    }
}
