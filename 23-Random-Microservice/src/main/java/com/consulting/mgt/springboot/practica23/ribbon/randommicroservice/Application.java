package com.consulting.mgt.springboot.practica23.ribbon.randommicroservice;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Random random() {
		return new Random();
	}
	
	@Bean
	@LoadBalanced // Definido despues, al hacer la integración con 23-Random-Microservice
	public RestTemplate loadBalancedRestTemplate() {
		return new RestTemplate();
	}
//
//	@Bean
//	public RandomService randomService(Random random) {
//		return () -> {
//			return random.nextInt(40);
//		};
//	}
//
//}
//
//@FunctionalInterface
//interface RandomService {
//	int next();
}