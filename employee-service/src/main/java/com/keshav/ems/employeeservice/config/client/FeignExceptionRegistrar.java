package com.keshav.ems.employeeservice.config.client;

public interface FeignExceptionRegistrar {
    void register(FeignExceptionRegistry registry);
}
