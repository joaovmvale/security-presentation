package com.securityDevelopment.rabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    private final static String QUEUE_NAME = "securityDev";

    @RabbitListener(queues = {QUEUE_NAME})
    public void receive(@Payload String fileBody) {
        System.out.println("Message " + fileBody);
    }

}
