package com.keshav.ems.employeeservice.config.client;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FeignRegistryInitializer {

    private final List<FeignExceptionRegistrar> registrars;

    private final FeignExceptionRegistry registry;

    @PostConstruct
    public void init() {
        registrars.forEach(r -> r.register(registry));
    }

}