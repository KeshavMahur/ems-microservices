package com.keshav.ems.employeeservice.service;

import com.keshav.ems.employeeservice.dto.common.ApiResponse;
import com.keshav.ems.employeeservice.dto.api.request.EmployeeRequestDto;
import com.keshav.ems.employeeservice.dto.api.request.EmployeeUpdateRequestDto;
import com.keshav.ems.employeeservice.dto.api.response.EmployeeResponseDto;
import com.keshav.ems.employeeservice.dto.api.response.EmployeeWithDepartmentResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeApiService {

    ResponseEntity<ApiResponse<EmployeeResponseDto>> generateEmployeeSaveDataHandlerResponse(@Valid EmployeeRequestDto dto);

    ResponseEntity<ApiResponse<EmployeeResponseDto>> generateEmployeeUpdateDataHandlerResponse(@Valid EmployeeUpdateRequestDto dto);

    ResponseEntity<ApiResponse<List<EmployeeResponseDto>>> generateEmployeeListDataHandlerResponse();

    ResponseEntity<ApiResponse<EmployeeWithDepartmentResponse>>generateFetchEmployeeByIdDataHandlerResponse(String id);
}
