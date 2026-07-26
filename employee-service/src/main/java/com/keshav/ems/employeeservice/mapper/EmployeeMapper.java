package com.keshav.ems.employeeservice.mapper;

import com.keshav.ems.employeeservice.dto.api.request.EmployeeRequestDto;
import com.keshav.ems.employeeservice.dto.api.response.EmployeeResponseDto;
import com.keshav.ems.employeeservice.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee dtoToEntity(EmployeeRequestDto dto) {
        Employee employee = new Employee();
        employee.setName(dto.name());
        employee.setAddress(dto.address());
        employee.setDesignation(dto.designation());
        employee.setSalary(dto.salary());
        employee.setDepartmentId(dto.departmentId());
        return employee;
    }

    public EmployeeResponseDto entityToDto(Employee employee) {
        return new EmployeeResponseDto(
                String.valueOf(employee.getId()),
                employee.getName(),
                employee.getSalary(),
                employee.getDepartmentId(),
                employee.getDesignation(),
                employee.getAddress(),
                employee.getCreatedDate().toString(),
                employee.getUpdatedDate().toString()
        );
    }

}
