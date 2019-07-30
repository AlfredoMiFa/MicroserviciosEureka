package com.consulting.mgt.springboot.practica23.ribbon.randommicroservice.restcontroller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consulting.mgt.springboot.practica23.ribbon.randommicroservice.MyListener;
import com.consulting.mgt.springboot.practica23.ribbon.randommicroservice.service.RandomService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RandomController {
	
	@Autowired
	private RandomService randomService;
	
	@Autowired
	private Environment enviroment;
	
	@GetMapping("/siguiente")
	public Map<String, Object> next() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("random", randomService.getRandomValueAge());
		map.put("from", "http://"+enviroment.getProperty("spring.application.name")+":"+MyListener.APPLICATION_PORT);
		log.info("Sale valor desde RandomController: {}",map);
		return map;
	}
	
}
