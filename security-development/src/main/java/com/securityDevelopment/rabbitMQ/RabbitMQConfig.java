package com.securityDevelopment.rabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue securityDevQueue() {
        return new Queue("securityDev", true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("securityDev-exchange");
    }

    @Bean
    Binding securityDevBinding(Queue securityDevQueue, DirectExchange exchange) {
        return BindingBuilder.bind(securityDevQueue).to(exchange).with("securityDev-routing-key");
    }

}
