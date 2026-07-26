package com.keshav.ems.employeeservice.dto.common;

public record FeignClientExceptionKey(
        String serviceName,
        String errorCode
) {
}
