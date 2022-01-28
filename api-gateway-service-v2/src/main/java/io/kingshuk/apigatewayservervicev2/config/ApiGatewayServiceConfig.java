package io.kingshuk.apigatewayservervicev2.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;


/**
 * A class for exploring routes through Spring Cloud Gateway
 */
@Configuration
public class ApiGatewayServiceConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes().route(p -> p.path("/get")
                                                                    .filters(f -> f.addRequestHeader("MyHeader", "MyHeaderValue")
                                                                                    .addRequestParameter("MyParam","MyParamValue"))
                                                                    .uri("http://httpbin.org:80"))
                // created custom route for the currency exchange service component,
                // now the complete uri through api gateway will look like: http://localhost:8765/currency-exchange-v2/from/USD/to/INR
                // instead of: http://localhost:8765/currency-exchange-service-v2/currency-exchange-v2/from/USD/to/INR
                .route(p -> p.path("/currency-exchange-v2/**").uri("lb://currency-exchange-service-v2"))

                // created custom route for the currency conversion service component,
                // now the complete uri through api gateway will look like: http://localhost:8765/currency-conversion-v2/from/USD/to/INR/quantity/100
                // instead of: http://localhost:8765/currency-conversion-service-v2/currency-conversion-v2/from/USD/to/INR/quantity/100 and
                // http://localhost:8765/currency-conversion-v2/feign/from/USD/to/INR/quantity/100 instead of: http://localhost:8765/currency-conversion-service-v2/currency-conversion-v2/feign/from/USD/to/INR
                .route(p -> p.path("/currency-conversion-v2/**").uri("lb://currency-conversion-service-v2"))
                .route(p -> p.path("/currency-conversion-new/**").filters(filters -> filters.rewritePath("/currency-conversion-new/(?<segment>.*)","/currency-conversion-v2/${segment}")).uri("lb://currency-conversion-service-v2"))
                .build();
    }
}
