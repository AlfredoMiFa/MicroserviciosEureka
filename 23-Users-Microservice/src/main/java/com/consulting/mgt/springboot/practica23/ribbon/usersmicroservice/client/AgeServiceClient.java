package com.consulting.mgt.springboot.practica23.ribbon.usersmicroservice.client;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AgeServiceClient {

	//Inyecta Bean Balanceado con Ribbon RestTemplate loadBalancedRestTemplate
	@Autowired
	private RestTemplate rest;

	@Value("${age-service-name:age-microservice}")
	private String serviceName;

	@SneakyThrows
	public int getAge() {
		
		URI uri = new URI(String.format("http://%s/age-service/age", serviceName));
		
		// Implementa
		Map<String, Object> objResponse = rest.getForObject(uri, Map.class);
		
		return (int) objResponse.get("age");
	}

}
