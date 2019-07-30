package com.consulting.mgt.springboot.practica23.ribbon.agemicroservice.client;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RandomServiceClient implements IRandomClient {

	@Autowired
	private Random random;
	
	@Override
	public int getRandomValue() {

		log.info("[local implementation] generating random value from");

		return random.nextInt(40);
	}

}
