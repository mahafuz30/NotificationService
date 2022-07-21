package com.learnkafka.service;

import com.learnkafka.Entity.SmsRequest;
import com.learnkafka.Entity.SmsRequestStatusType;
import com.learnkafka.repository.SmsRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SmsRequestService {
    @Autowired
    SmsRequestRepository smsRequestRepository;

    public List<SmsRequest> getAllMessage(){
        return (List<SmsRequest>) smsRequestRepository.findAll();
    }

    public SmsRequest sendMessage(SmsRequest smsRequest){
        smsRequest.setSmsRequestStatusType(SmsRequestStatusType.REQUESTED);
        return smsRequestRepository.save(smsRequest);
    }
}
