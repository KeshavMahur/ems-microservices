package com.keshav.ems.employeeservice.client.executor;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResilienceExecutor {

    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final RetryRegistry retryRegistry;
    private final BulkheadRegistry bulkheadRegistry;
    private final RateLimiterRegistry rateLimiterRegistry;

    public <T> T execute(String serviceName, Supplier<T> supplier, Function<Throwable, T> fallback) {

        /**
         * service name isliye pass kra kyuki har service ki config alag hoti hai
         *toh sabse phele config lega then object dega
         */


        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(serviceName);
        Retry retry = retryRegistry.retry(serviceName);
        Bulkhead bulkhead = bulkheadRegistry.bulkhead(serviceName);
        RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter(serviceName);

        //main supplier call toh other service
        Supplier<T> decoratedSupplier = supplier;

        //layer 4
        decoratedSupplier = Retry.decorateSupplier(retry, decoratedSupplier);


        // layer 3
        decoratedSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, decoratedSupplier);

        // layer 2
        decoratedSupplier = Bulkhead.decorateSupplier(bulkhead, decoratedSupplier);

        //layer 1
        decoratedSupplier = RateLimiter.decorateSupplier(rateLimiter, decoratedSupplier);

        try {
            return decoratedSupplier.get();
        } catch (Throwable ex) {
            log.error("Fallback Executed", ex);
            return fallback.apply(ex);
        }

    }
}