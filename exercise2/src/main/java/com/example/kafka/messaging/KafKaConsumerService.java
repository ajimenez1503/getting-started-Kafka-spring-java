package com.example.kafka.messaging;

import com.example.kafka.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafKaConsumerService {
    private final Logger logger =
            LoggerFactory.getLogger(KafKaConsumerService.class);

    @KafkaListener(topics = "#{'${spring.kafka.topic.name}'}", groupId = "#{'${spring.kafka.consumer.group-id}'}")
    public void consume(Message message) {
        logger.info("Message received {}", message);
    }
}
