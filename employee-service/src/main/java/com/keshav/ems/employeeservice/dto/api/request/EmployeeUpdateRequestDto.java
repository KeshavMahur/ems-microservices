package com.keshav.ems.employeeservice.dto.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EmployeeUpdateRequestDto(
        @NotBlank(message = "Id can't blank")
        String id,

        @NotBlank(message = "name can't blank")
        String name,

        @NotBlank(message = "Salary can't blank")
        @Min(value = 1,message = "Salary must be 1")
        Double salary,

        @NotBlank(message = "Department Id can't blank")
        String departmentId,

        @NotBlank(message = "Designation can't blank")
        String designation,

        String address
) {
}
