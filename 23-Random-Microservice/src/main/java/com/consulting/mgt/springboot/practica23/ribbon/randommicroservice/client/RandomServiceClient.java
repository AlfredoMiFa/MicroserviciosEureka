package com.consulting.mgt.springboot.practica23.ribbon.randommicroservice.client;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Primary
@Service
public class RandomServiceClient implements RandomServiceI {
	
	@Autowired()
	private Random random;
	
	@Override
	@SneakyThrows
	public int randomNext() {
		log.info("Implementacion de numero aleatorio desde otro servicio");
		return random.nextInt(50);
	}
	
}
