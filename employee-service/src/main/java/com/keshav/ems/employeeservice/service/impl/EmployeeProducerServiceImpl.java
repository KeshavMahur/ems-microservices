package com.keshav.ems.employeeservice.service.impl;

import com.keshav.ems.employeeservice.dto.common.EmployeeCreatedEvent;
import com.keshav.ems.employeeservice.service.EmployeeProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProducerServiceImpl implements EmployeeProducer {

    private static final String TOPIC = "employee";
    private final KafkaTemplate<String, EmployeeCreatedEvent> kafkaTemplate;

    @Override
    public void publish(EmployeeCreatedEvent employeeCreatedEvent) {
        
    }
}
