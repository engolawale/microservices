package com.leadspring.microservices.currencyexchange.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;

import com.leadspring.microservices.currencyexchange.bean.ExchangeValueDTO;
import com.leadspring.microservices.currencyexchange.service.CurrencyExchangeService;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment enviroment;
	
	@Autowired
	private CurrencyExchangeService currencyExchangeService;

	@GetMapping("/from/{from}/to/{to}")
	public ExchangeValueDTO retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValueDTO exchangeValue = 
				new ExchangeValueDTO(Long.valueOf(1), "USD", "INR", BigDecimal.valueOf(75));
		exchangeValue.setPort(
				Integer.parseInt(enviroment.getProperty("local.server.port")));
		return exchangeValue;
	}

	
	@GetMapping("/jpa/from/{from}/to/{to}")
	public ExchangeValueDTO retrieveJpaExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValueDTO exchangeValue = 
				currencyExchangeService.retrieveExchangeValue(from, to);
		exchangeValue.setPort(
				Integer.parseInt(enviroment.getProperty("local.server.port")));
		logger.info("{}",exchangeValue);
		return exchangeValue;
	}
}
