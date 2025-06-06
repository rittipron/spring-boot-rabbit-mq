package com.example.spring_boot_rabbit_mq.consumer;
import com.example.spring_boot_rabbit_mq.dto.MessagePayload;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "#{rabbitMQProperties.queue}")
    public void consume(MessagePayload payload) {
        System.out.println("✔️ Received: " + payload);

        if ("fail".equalsIgnoreCase(payload.getContent())) {
            throw new RuntimeException("💥 Simulated processing failure");
        }
    }
}