package com.keshav.ems.employeeservice.service.impl;

import com.keshav.ems.employeeservice.client.department.gateway.DepartmentGateway;
import com.keshav.ems.employeeservice.dto.common.ApiResponse;
import com.keshav.ems.employeeservice.dto.api.request.EmployeeRequestDto;
import com.keshav.ems.employeeservice.dto.api.request.EmployeeUpdateRequestDto;
import com.keshav.ems.employeeservice.dto.api.response.EmployeeResponseDto;
import com.keshav.ems.employeeservice.dto.api.response.EmployeeWithDepartmentResponse;
import com.keshav.ems.employeeservice.dto.client.response.DepartmentResponse;
import com.keshav.ems.employeeservice.dto.common.EmployeeCreatedEvent;
import com.keshav.ems.employeeservice.entity.Employee;
import com.keshav.ems.employeeservice.exceptions.custom.EmployeeNotFoundExceptions;
import com.keshav.ems.employeeservice.mapper.EmployeeMapper;
import com.keshav.ems.employeeservice.repository.EmployeeRepository;
import com.keshav.ems.employeeservice.service.EmployeeApiService;
import com.keshav.ems.employeeservice.service.EmployeeProducer;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeApiServiceImpl implements EmployeeApiService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentGateway departmentGateway;
    private final EmployeeProducer producer;

    @Override
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> generateEmployeeSaveDataHandlerResponse(EmployeeRequestDto dto) {
        Employee employee = employeeRepository.save(employeeMapper.dtoToEntity(dto));
        ResponseEntity<ApiResponse<DepartmentResponse>> departmentResponse = departmentGateway.getDepartment(employee.getDepartmentId());

        EmployeeCreatedEvent event = EmployeeCreatedEvent.builder()

                .employeeId(employee.getId())

                .employeeName(employee.getName())

                .address(employee.getAddress())

                .departmentName(departmentResponse.getBody().data().departmentName())

                .build();

        producer.publish(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success(employeeMapper.entityToDto(employee))
        );
    }

    @Override
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> generateEmployeeUpdateDataHandlerResponse(EmployeeUpdateRequestDto dto) {
        var id = dto.id();
        Employee employee = employeeRepository.findById(Long.parseLong(id)).orElse(null);
        if (employee == null) {
            throw new EmployeeNotFoundExceptions("Employee does not exists with id");
        }


        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<List<EmployeeResponseDto>>> generateEmployeeListDataHandlerResponse() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponseDto> dtos = employees.stream().map(employee -> employeeMapper.entityToDto(employee)).toList();
        return ResponseEntity.ok(ApiResponse.success("Employee fetch successfully.", dtos));
    }

    @Override
    public ResponseEntity<ApiResponse<EmployeeWithDepartmentResponse>> generateFetchEmployeeByIdDataHandlerResponse(String id) {

        Employee employee = employeeRepository.findById(Long.parseLong(id)).orElse(null);
        if (employee == null) {
            throw new EmployeeNotFoundExceptions("Employee does not exists with id");
        }

        String departmentId = employee.getDepartmentId();
        if (StringUtils.isEmpty(departmentId)) {
            throw new EmployeeNotFoundExceptions("Department Id is empty");
        }

        ResponseEntity<ApiResponse<DepartmentResponse>> departmentResponse = departmentGateway.getDepartment(departmentId);
        EmployeeResponseDto employeeResponseDto = employeeMapper.entityToDto(employee);

        EmployeeWithDepartmentResponse employeeWithDepartmentResponse = new EmployeeWithDepartmentResponse(
                employeeResponseDto, departmentResponse.getBody().data()
        );

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.success("Employee data with department fetch successfully.", employeeWithDepartmentResponse)
        );
    }
}
