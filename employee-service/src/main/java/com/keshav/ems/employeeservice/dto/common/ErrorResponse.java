package com.keshav.ems.employeeservice.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record ErrorResponse(
        Instant timestamp,
        int httpStatusCode,
        String httpStatusMessage,
        String serviceName,
        String serviceErrorCode,
        String message,
        String path,
        List<FieldErrorResponse> errorResponses
) { }


