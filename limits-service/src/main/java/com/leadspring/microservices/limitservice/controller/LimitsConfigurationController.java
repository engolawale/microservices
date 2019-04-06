package com.leadspring.microservices.limitservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leadspring.microservices.limitservice.Configuration;
import com.leadspring.microservices.limitservice.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/limit")
public class LimitsConfigurationController {
	
	private Configuration configuration;
	
	
	@Autowired
	public LimitsConfigurationController(Configuration configuration) {
		this.configuration = configuration;
	}



	@GetMapping("/limits")
	public LimitConfiguration retriveLimitConfigurations() {
		
		return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());	
	}
	
	@GetMapping("/fault-tolerant/limits")
	@HystrixCommand(fallbackMethod = "fallbackRetriveLimitConfigurations")
	public LimitConfiguration fualtTolerantLimitConfigurations() {
		throw new RuntimeException("Not Available");
		//return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());	
	}
	
	public LimitConfiguration fallbackRetriveLimitConfigurations() {
	
		return new LimitConfiguration(6, 10);	
	}

}
