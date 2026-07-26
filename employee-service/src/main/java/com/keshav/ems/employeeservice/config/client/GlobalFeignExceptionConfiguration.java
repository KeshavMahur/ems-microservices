package com.keshav.ems.employeeservice.config.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GlobalFeignExceptionConfiguration {

    private final ObjectMapper objectMapper;
    private final FeignExceptionRegistry registry;

    @Bean
     public ErrorDecoder customErrorDecoder() {

        return new FeignErrorDecoder(objectMapper, registry);
    }

}
