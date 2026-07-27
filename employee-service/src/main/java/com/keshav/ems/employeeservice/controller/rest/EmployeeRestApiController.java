package com.keshav.ems.employeeservice.controller.rest;

import com.keshav.ems.employeeservice.dto.common.ApiResponse;
import com.keshav.ems.employeeservice.dto.api.request.EmployeeRequestDto;
import com.keshav.ems.employeeservice.dto.api.request.EmployeeUpdateRequestDto;
import com.keshav.ems.employeeservice.dto.api.response.EmployeeResponseDto;
import com.keshav.ems.employeeservice.dto.api.response.EmployeeWithDepartmentResponse;
import com.keshav.ems.employeeservice.service.EmployeeApiService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/employees/")
@AllArgsConstructor
@RestController
public class EmployeeRestApiController {

    private final EmployeeApiService employeeApiService;

    @PostMapping("save")
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> employeeSaveDataHandler(@RequestBody @Valid EmployeeRequestDto dto){
        return employeeApiService.generateEmployeeSaveDataHandlerResponse(dto);
    }

    @PutMapping("update")
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> employeeUpdateDataHandler(@RequestBody @Valid EmployeeUpdateRequestDto dto){
        return employeeApiService.generateEmployeeUpdateDataHandlerResponse(dto);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<EmployeeResponseDto>>> employeeListDataHandler(){
        return employeeApiService.generateEmployeeListDataHandlerResponse();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ApiResponse<EmployeeWithDepartmentResponse>>fetchEmployeeByIdDataHandler(@PathVariable String id){
        return employeeApiService.generateFetchEmployeeByIdDataHandlerResponse(id);
    }
 }
