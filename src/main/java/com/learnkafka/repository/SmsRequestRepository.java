package com.learnkafka.repository;

import com.learnkafka.Entity.SmsRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface SmsRequestRepository extends CrudRepository<SmsRequest,Long> {
}
