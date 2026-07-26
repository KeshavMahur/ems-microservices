package com.keshav.ems.employeeservice.exceptions.custom;


public class EmployeeNotFoundExceptions extends RuntimeException {
    public EmployeeNotFoundExceptions(String message) {
        super(message);
    }
}
