package com.keshav.ems.employeeservice.exceptions.custom;

public class UnknownFeignException extends RuntimeException {
    public UnknownFeignException(String message) {
        super(message);
    }
}
