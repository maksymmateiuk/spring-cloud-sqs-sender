package com.mmateiuk.springcloudsqssender.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageProducer {

    @Value("${orders.queue.name}")
    private String queue;

    private final QueueMessagingTemplate queueMessagingTemplate;

    public MessageProducer(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    @SneakyThrows
    public <T> void sendMessage(T message) {

        String messageBody = new ObjectMapper().writeValueAsString(message);

        log.info("{} Send message to amazon SQS: {}", this.getClass().getSimpleName(), messageBody);
        log.info("{} To queue: {}", this.getClass().getSimpleName(), queue);

        queueMessagingTemplate.convertAndSend(queue, messageBody);
    }

}
