package com.learnkafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learnkafka.Entity.SmsRequest;
import com.learnkafka.producer.SmsMessageProducer;
import com.learnkafka.service.SmsRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/sms")
public class SmsRequestController {

    @Autowired
    SmsRequestService smsRequestService;

    @Autowired
    private SmsMessageProducer smsMessageProducer;

    @GetMapping("all")
    public ResponseEntity<List<SmsRequest>> getAllMessage(){
        return ResponseEntity.status(HttpStatus.OK).body(smsRequestService.getAllMessage());
    }

    @PostMapping("send")
    public ResponseEntity<?> sendMessage(@RequestBody SmsRequest smsRequest) throws JsonProcessingException {
        if (smsRequest.getNumber()==null || smsRequest.getNumber().toString().length()<10){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Number is not valid");
        }

        if (smsRequest.getMessage()==null || smsRequest.getMessage().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message Field Required!");
        }
        SmsRequest smsRequestDb = smsRequestService.sendMessage(smsRequest);
        smsMessageProducer.sendSmsRequestEvent(smsRequestDb);
        return ResponseEntity.status(HttpStatus.CREATED).body(smsRequestDb);
    }

}
