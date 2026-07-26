package com.keshav.ems.employeeservice.client.department.gateway;

import com.keshav.ems.employeeservice.client.department.DepartmentClient;
import com.keshav.ems.employeeservice.client.executor.ResilienceExecutor;
import com.keshav.ems.employeeservice.dto.client.response.DepartmentResponse;
import com.keshav.ems.employeeservice.dto.common.ApiResponse;
import com.keshav.ems.employeeservice.exceptions.client.DepartmentNotFoundException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
@Slf4j
public class DepartmentGateway {

    private final DepartmentClient departmentClient;
    private final ResilienceExecutor resilienceExecutor;

    /*@CircuitBreaker(name = "departmentService", fallbackMethod = "departmentFallback")
    @Retry(name = "departmentService")
    @Bulkhead(name = "departmentService", type = Bulkhead.Type.SEMAPHORE)
    @RateLimiter(name = "departmentService")*/

    public ResponseEntity<ApiResponse<DepartmentResponse>> getDepartment(String departmentId) {
        //return departmentClient.departmentEmployeeDataHandlerResponse(departmentId);

        return resilienceExecutor.execute(

                "departmentService",

                () -> departmentClient.departmentEmployeeDataHandlerResponse(departmentId),

                ex -> departmentFallback(departmentId, ex)

        );
    }


    //---------------- Fallback ----------------//
    private ResponseEntity<ApiResponse<DepartmentResponse>> departmentFallback(
            String departmentId,
            Throwable ex) {

        log.error("Department Service Failed for departmentId : {}",
                departmentId,
                ex);

        // Business Exception → propagate
        if (ex instanceof DepartmentNotFoundException) {
            throw (DepartmentNotFoundException) ex;
        }

        DepartmentResponse response = DepartmentResponse.builder()
                .departmentId(departmentId)
                .departmentName("Department Service Unavailable")
                .build();

        ApiResponse apiResponse =
                        ApiResponse.builder().success(false)
                        .message("Department information is temporarily unavailable.")
                        .data(response).timestamp(Instant.now()).build();

        return ResponseEntity.ok(apiResponse);
    }

}
