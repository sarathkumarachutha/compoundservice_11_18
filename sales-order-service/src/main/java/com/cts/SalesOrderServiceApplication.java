package com.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 
 * Main Class to start(bootstrap)/launch a spring boot Application
 *
 */

@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableFeignClients
//@EnableEurekaClient
@SpringBootApplication
public class SalesOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesOrderServiceApplication.class, args);
	}

}
