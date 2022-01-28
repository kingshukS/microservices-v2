package io.kingshuk.apigatewayservervicev2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayServiceV2Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServiceV2Application.class, args);
	}

}
