package com.keshav.ems.employeeservice.dto.common;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record ApiResponse <T>(
        boolean success,
        String message,
        T data,
        Instant timestamp
){
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(
                true,
                message,
                data,
                Instant.now()
        );
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(
                true,
                "Request processed successfully.",
                data,
                Instant.now()
        );
    }

    public static ApiResponse<Void> success(String message) {
        return new ApiResponse<>(
                true,
                message,
                null,
                Instant.now()
        );
    }

    public static ApiResponse<Void> failure(String message) {
        return new ApiResponse<>(
                false,
                message,
                null,
                Instant.now()
        );
    }
}
