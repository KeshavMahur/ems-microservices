package com.keshav.ems.employeeservice.dto.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.keshav.ems.employeeservice.dto.client.response.DepartmentResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EmployeeWithDepartmentResponse(
        EmployeeResponseDto employeeResponseDto,
        DepartmentResponse departmentResponseDto
) {
}
