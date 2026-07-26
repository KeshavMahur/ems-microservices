package com.keshav.ems.employeeservice.dto.client.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record DepartmentResponse(
        String departmentId,
        String departmentName,
        String departmentCode,
        String departmentType,
        String departmentDescription,
        String createdDate,
        String updatedDate
) {}