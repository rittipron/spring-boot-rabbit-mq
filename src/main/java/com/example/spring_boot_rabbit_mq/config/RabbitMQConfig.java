package com.example.spring_boot_rabbit_mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final RabbitMQProperties props;

    public static final String EXCHANGE = "demo_exchange";
    public static final String ROUTING_KEY = "demo_routingKey";

    public static final String DEAD_LETTER_QUEUE = "dead_letter_queue";
    public static final String DEAD_LETTER_EXCHANGE = "dead_letter_exchange";
    public static final String DEAD_LETTER_ROUTING_KEY = "dead_letter_routingKey";

    public RabbitMQConfig (RabbitMQProperties props){
        this.props = props;
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(props.getQueue())
                .withArgument("x-dead-letter-exchange", props.getDeadLetterExchange())
                .withArgument("x-dead-letter-routing-key", props.getDeadLetterQueue())
                .build();
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(props.getExchange());
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(props.getRoutingKey());
    }

    @Bean
    public Queue deadLetterQueue() {
        return new Queue(props.getDeadLetterQueue());
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(props.getDeadLetterExchange());
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(props.getDeadLetterRoutingKey());
    }
}