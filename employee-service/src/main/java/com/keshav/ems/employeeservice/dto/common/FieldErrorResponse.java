package com.keshav.ems.employeeservice.dto.common;

public record FieldErrorResponse(
        String field,
        Object rejectedValue,
        String reason
) {
}
