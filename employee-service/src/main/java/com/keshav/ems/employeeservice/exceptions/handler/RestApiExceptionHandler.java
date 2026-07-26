package com.keshav.ems.employeeservice.exceptions.handler;

import com.keshav.ems.employeeservice.dto.common.ErrorResponse;
import com.keshav.ems.employeeservice.exceptions.custom.EmployeeNotFoundExceptions;
import com.keshav.ems.employeeservice.exceptions.custom.UnknownFeignException;
import com.keshav.ems.employeeservice.exceptions.client.DepartmentNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;


import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Slf4j
public class RestApiExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundExceptions.class)
    public ResponseEntity<ErrorResponse> employeeExceptionHandler(EmployeeNotFoundExceptions employeeNotFoundExceptions, HttpServletRequest request) {
        log.error(employeeNotFoundExceptions.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .httpStatusCode(NOT_FOUND.value())
                .httpStatusMessage(NOT_FOUND.name())
                .serviceName("employee-service")
                .serviceErrorCode("employee-404")
                .message(employeeNotFoundExceptions.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDepartNotFoundException(DepartmentNotFoundException exception, HttpServletRequest request) {
        log.error(exception.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .httpStatusCode(NOT_FOUND.value())
                .httpStatusMessage(NOT_FOUND.name())
                .serviceName("department-service")
                .serviceErrorCode("Department_Not_Found")
                .message(exception.getMessage())
                .path(request.getRequestURI()).build();
        return ResponseEntity.status(NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(UnknownFeignException.class)
    private ResponseEntity<ErrorResponse> buildErrorResponse(
            HttpStatus status,
            String message) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .httpStatusCode(status.value())
                .httpStatusMessage(status.getReasonPhrase())
                .message(message)
                .serviceName("employee-service")
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.status(status).body(errorResponse);
    }
}
