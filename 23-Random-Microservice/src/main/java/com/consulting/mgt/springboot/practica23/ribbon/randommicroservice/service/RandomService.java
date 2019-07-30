package com.consulting.mgt.springboot.practica23.ribbon.randommicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consulting.mgt.springboot.practica23.ribbon.randommicroservice.client.RandomServiceI;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RandomService {
	
	@Autowired
	private RandomServiceI randomServiceClient;
	
	public int getRandomValueAge() {
		log.info("Antes de obtener le valor desde el microservicio de Random");
		int age = randomServiceClient.randomNext();
		log.info("Se obtiene la edad con valor de: {}", age);
		log.info("Se envia informacion a age-microservice");
		return age;
	}
	
}
