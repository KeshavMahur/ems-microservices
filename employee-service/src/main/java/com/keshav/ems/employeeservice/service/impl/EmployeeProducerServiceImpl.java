package com.keshav.ems.employeeservice.service.impl;

import com.keshav.ems.employeeservice.dto.common.EmployeeCreatedEvent;
import com.keshav.ems.employeeservice.service.EmployeeProducer;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class EmployeeProducerServiceImpl implements EmployeeProducer {

    private static final String TOPIC = "employee";
    private final KafkaTemplate<String, EmployeeCreatedEvent> kafkaTemplate;

    @Override
    public void publish(EmployeeCreatedEvent event) {
        log.info("Publishing Employee Event {}", event);
        kafkaTemplate.send(TOPIC,event.getEmployeeId().toString() ,event);
    }
}
