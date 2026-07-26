package com.keshav.ems.employeeservice.client.department;

import com.keshav.ems.employeeservice.dto.common.ApiResponse;
import com.keshav.ems.employeeservice.dto.client.response.DepartmentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "department-service")
public interface DepartmentClient {

    @GetMapping("/id/{id}")
    ResponseEntity<ApiResponse<DepartmentResponse>> departmentEmployeeDataHandlerResponse(@PathVariable String id);
}
