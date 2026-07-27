package com.keshav.ems.notificationservice.serviceImpl;

import com.keshav.ems.notificationservice.event.dto.common.EmployeeCreatedEvent;
import com.keshav.ems.notificationservice.service.EmployeeCreatedConsumer;
import com.keshav.ems.notificationservice.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class EmployeeCreatedConsumerImpl implements EmployeeCreatedConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = "employee",
            groupId = "notification-group"
    )

    public void consume(EmployeeCreatedEvent event){

        log.info("Received Event {}", event);

        notificationService.save(event);

    }
}
