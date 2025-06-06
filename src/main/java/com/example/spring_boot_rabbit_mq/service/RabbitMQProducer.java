package com.example.spring_boot_rabbit_mq.service;

import com.example.spring_boot_rabbit_mq.config.RabbitMQProperties;
import com.example.spring_boot_rabbit_mq.dto.MessagePayload;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private final RabbitMQProperties rabbitProps;

    private final AmqpTemplate amqpTemplate;

    public RabbitMQProducer(RabbitMQProperties rabbitProps, AmqpTemplate amqpTemplate) {
        this.rabbitProps = rabbitProps;
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMessage(MessagePayload payload) {
        amqpTemplate.convertAndSend(rabbitProps.getExchange(), rabbitProps.getRoutingKey(), payload);
    }
}
