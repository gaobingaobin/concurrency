package rabbitmq.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rabbitmq.RabbitMqConfig;
/**
* @Description: 生产者
* @author gaobin
* @createDate 2019/1/16 16:07
*/
@Slf4j
@Component
public class FirstSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 发送消息
     * @param uuid
     * @param message  消息
     */
    public void send(String uuid,Object message) {
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY2,
                message, correlationId);
    }

}
