package com.keshav.ems.notificationservice.service;

import com.keshav.ems.notificationservice.event.dto.common.EmployeeCreatedEvent;

public interface EmployeeCreatedConsumer {
    public void consume(EmployeeCreatedEvent event);
}
