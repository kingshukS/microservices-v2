package io.kingshuk.currencyexchangeservice.resource;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/resilience4j")
public class Resilience4jResource {

    private final static Logger logger = LoggerFactory.getLogger(Resilience4jResource.class.getName());

    @GetMapping("/retry")
    @Retry(name = "sample-api-retry", fallbackMethod = "fallbackMethod")
    public String retry(){
        logger.info("--retry(): retrying to get API response from dummy uri, which will fail");
        ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:8080/dummy-url", String.class);
        return response.getBody();
    }

    @GetMapping("/circuit-breaker")
    @CircuitBreaker(name = "sample-api-circuit-breaker", fallbackMethod = "fallbackMethod")
    public String circuitBreaker(){
        logger.info("--circuitBreaker(): circuit breaker demo");
        ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:8080/dummy-url", String.class);
        return response.getBody();
    }

    @GetMapping("/rate-limiter")
    @RateLimiter(name = "sample-api-rate-limiter", fallbackMethod = "fallbackMethod")
    public String rateLimiter(){
        logger.info("--rateLimiter(): rateLimiter demo");
        return "sample rate limiter output";
    }

    @GetMapping("/bulkhead")
    @Bulkhead(name = "sample-api-bulkhead", fallbackMethod = "fallbackMethod")
    public String bulkhead() throws InterruptedException {
        logger.info("--bulkhead(): bulkhead demo");
        return "sample rate bulkhead output";
    }

    public String fallbackMethod(Exception ex){
        logger.info("--fallbackMethod(): fallback method exception={}",ex.getMessage());
        return "fallback response: default";
    }
}
