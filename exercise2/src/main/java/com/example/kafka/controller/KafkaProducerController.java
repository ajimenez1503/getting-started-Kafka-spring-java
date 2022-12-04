package com.example.kafka.controller;

import com.example.kafka.messaging.KafKaProducerService;
import com.example.kafka.model.Message;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
@AllArgsConstructor
@Validated
public class KafkaProducerController
{
    private final KafKaProducerService producerService;

    @PostMapping(value = "/publish", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sendMessageToKafkaTopic(@RequestBody Message message)
    {
        this.producerService.sendMessage(message);
    }
}
