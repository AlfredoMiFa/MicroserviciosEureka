package com.consulting.mgt.springboot.practica23.ribbon.usersmicroservice.client;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Primary
@Service
public class AppiRebbonUppercaseServiceClient implements IUppercaseService {
	
	@Autowired
	private LoadBalancerClient loadBalancer;

	private RestTemplate loadBalancedRestTemplate = new RestTemplate();

	@Value("${uppercase-service-name:uppercase-microservice}")
	private String serviceName;

	@Override
	@SneakyThrows
	public String toUppercase(String name) {

		ServiceInstance instance = loadBalancer.choose(serviceName);
		
		// build URI using service-name
		URI uri = new URI(String.format("http://%s:%s/uppercase-service/toUppercase/%s",
				instance.getHost(), instance.getPort(), name));

		// getting map from loadBalanced RestTemplate
		Map<String, Object> mapResponse = loadBalancedRestTemplate.getForObject(uri, Map.class);

		log.info("{} response Rebborn: {}", serviceName, mapResponse);

		return (String) mapResponse.get("uppercase");
	}

}
