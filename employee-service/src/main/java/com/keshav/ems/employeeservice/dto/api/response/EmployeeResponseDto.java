package com.keshav.ems.employeeservice.dto.api.response;

public record EmployeeResponseDto(
        String id,
        String name,
        Double salary,
        String departmentId,
        String designation,
        String address,
        String createdDate,
        String updateDate
) {
}
