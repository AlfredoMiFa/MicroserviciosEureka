package com.consulting.mgt.springboot.practica23.ribbon.agemicroservice.client;

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
@Primary
@Service
public class RandomServiceClientBalanced implements IRandomClient{
	
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	private RestTemplate loadBalancedRestTemplate = new RestTemplate();
	
	@Value("${random-service-name:random-microservice}")
	private String serviceName;

	@SneakyThrows
	@Override
	public int getRandomValue() {
		log.info("Obteniendo valor de edad desde loadBalancer");
		ServiceInstance instance = loadBalancer.choose(serviceName);
		log.info("Puerto de escucha: {}", instance.getPort());
		log.info("Host a conectarse: {}", instance.getHost());
		URI uri = new URI(String.format("http://%s:%s/random-service/siguiente", 
				instance.getHost(), instance.getPort()));
		log.info("URI: {}",uri);
		Map<String, Object> mapResponse = loadBalancedRestTemplate.getForObject(uri, Map.class);
		log.info("{} Response loadBalancer: {}", serviceName, mapResponse);
		return (int) mapResponse.get("random");
	}
	
}
