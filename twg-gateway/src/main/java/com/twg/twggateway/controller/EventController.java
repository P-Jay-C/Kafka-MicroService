package com.twg.twggateway.controller;

import com.twg.twggateway.models.TransactionMessage;
import com.twg.twggateway.services.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class EventController {

    @Autowired
    KafkaProducerService kafkaProducerService;

    @PostMapping("/event")
    ResponseEntity<String> event(@RequestBody TransactionMessage transactionMessage){
        UUID uuid = UUID.randomUUID();

        log.info("We received the transaction with the key " + uuid);
        kafkaProducerService.send("transaction-topic", uuid, transactionMessage);

        return ResponseEntity.ok("Response sent");
    }

}
