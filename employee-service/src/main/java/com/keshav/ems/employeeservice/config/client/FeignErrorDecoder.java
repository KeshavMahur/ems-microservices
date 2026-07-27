package com.keshav.ems.employeeservice.config.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keshav.ems.employeeservice.dto.common.ErrorResponse;
import com.keshav.ems.employeeservice.exceptions.custom.UnknownFeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    private final FeignExceptionRegistry registry;


    @Override
    public Exception decode(String methodKey, Response response) {
        try {

            if (response.body() == null) {
                return new UnknownFeignException(
                        "Empty error response from downstream service."
                );
            }

            ErrorResponse errorResponse =
                    objectMapper.readValue(
                            response.body().asInputStream(),
                            ErrorResponse.class
                    );

            return registry.getFeignClientException(errorResponse);
        } catch (Exception e) {
            return new UnknownFeignException(
                    "Unexpected error received from downstream service."
            );
        }
    }

}
