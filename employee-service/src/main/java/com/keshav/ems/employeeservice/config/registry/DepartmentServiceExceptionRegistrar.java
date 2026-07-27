package com.keshav.ems.employeeservice.config.registry;

import com.keshav.ems.employeeservice.config.client.FeignExceptionRegistrar;
import com.keshav.ems.employeeservice.config.client.FeignExceptionRegistry;
import com.keshav.ems.employeeservice.exceptions.client.DepartmentNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DepartmentServiceExceptionRegistrar implements FeignExceptionRegistrar {

    @Override
    public void register(FeignExceptionRegistry registry) {

        registry.registerFeignClientExceptions(
                "department-service",
                "DEPT_404",
                errorResponse -> new DepartmentNotFoundException(errorResponse.message()));

       /* registry.register(
                "department-service",
                "DEPT_409",
                error -> new DepartmentAlreadyExistsException(error.message())
        );*/
    }
}
