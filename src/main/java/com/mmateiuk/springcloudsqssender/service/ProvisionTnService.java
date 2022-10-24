package com.mmateiuk.springcloudsqssender.service;

import com.mmateiuk.springcloudsqssender.model.common.TnDetail;
import com.mmateiuk.springcloudsqssender.producer.MessageProducer;
import java.util.Date;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProvisionTnService {

    private final MessageProducer messageProducer;

    public ProvisionTnService(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    public TnDetail provision(TnDetail tnDetail) {

        log.info("{} Sent message to amazon SQS: {}", this.getClass().getSimpleName(), tnDetail);

        messageProducer.sendMessage(tnDetail);

        return tnDetail;
    }
}
