package com.mmateiuk.springcloudsqssender.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmateiuk.springcloudsqssender.model.common.Event;
import com.mmateiuk.springcloudsqssender.model.common.TnDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

    private static final ObjectMapper OBJECT_MAPPER = Jackson2ObjectMapperBuilder.json().build();

    @SqsListener(value = "${orders.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void processMessage(String message) {
        try {
            Event event = OBJECT_MAPPER.readValue(message, Event.class);
            log.info("Received new SQS message and delete from queue: {}", message);
            log.info("Convert to Event.class: {}", event);
        } catch (Exception e) {
            throw new RuntimeException("Cannot process message from SQS", e);
        }
    }

}
