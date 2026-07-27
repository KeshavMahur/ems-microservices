package com.keshav.ems.employeeservice.service;

import com.keshav.ems.employeeservice.dto.common.EmployeeCreatedEvent;

public interface EmployeeProducer {
    public void publish(EmployeeCreatedEvent employeeCreatedEvent);
}
