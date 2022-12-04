package com.example.kafka.messaging;

import com.example.kafka.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafKaProducerService
{
    private static final Logger logger =
            LoggerFactory.getLogger(KafKaProducerService.class);

    @Value(value = "${spring.kafka.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public void sendMessage(Message message)
    {
        logger.info("Message sent {}", message);
        this.kafkaTemplate.send(topicName, message);
    }
}