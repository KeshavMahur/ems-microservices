package com.keshav.ems.employeeservice.config.client;

import com.keshav.ems.employeeservice.dto.common.ErrorResponse;
import com.keshav.ems.employeeservice.dto.common.FeignClientExceptionKey;
import com.keshav.ems.employeeservice.exceptions.custom.UnknownFeignException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Component
public class FeignExceptionRegistry {

    private final  Map<FeignClientExceptionKey, Function<ErrorResponse,RuntimeException>> registry = new ConcurrentHashMap<>();


    public FeignExceptionRegistry(List<FeignExceptionRegistrar> registrars) {

        registrars.forEach(registrar -> registrar.register(this));
    }

    public void registerFeignClientExceptions(String serviceName, String errorCode, Function<ErrorResponse,RuntimeException> supplier) {
        registry.put(new FeignClientExceptionKey(serviceName, errorCode), supplier);
    }

    public RuntimeException getFeignClientException(ErrorResponse error) {

        return registry.getOrDefault(
                        new FeignClientExceptionKey(error.serviceName(), error.serviceErrorCode()),
                        e -> new UnknownFeignException(e.message())
                )
                .apply(error);

    }
}
