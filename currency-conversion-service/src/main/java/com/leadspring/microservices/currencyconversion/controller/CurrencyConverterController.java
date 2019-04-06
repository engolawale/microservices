package com.leadspring.microservices.currencyconversion.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leadspring.microservices.currencyconversion.bean.CurrencyConversionBean;
import com.leadspring.microservices.currencyconversion.service.CurrencyConverterService;

@RestController
@RequestMapping("/currency-converter")
public class CurrencyConverterController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment enviroment;
	
	@Autowired
	private CurrencyConverterService currencyConverterService;
	
	@GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,@PathVariable String quantity) {
		//int port = Integer.parseInt(enviroment.getProperty("local.server.port"));
		
		CurrencyConversionBean currencyConversionBean = new CurrencyConversionBean();
		currencyConversionBean = currencyConverterService.convertCurrency(from, to, BigDecimal.valueOf(Long.valueOf(quantity)));
				
		return currencyConversionBean;
		
	}
	
	
	@GetMapping("/feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,@PathVariable String quantity) {
		//int port = Integer.parseInt(enviroment.getProperty("local.server.port"));
		
		CurrencyConversionBean currencyConversionBean = new CurrencyConversionBean();
		currencyConversionBean = currencyConverterService.convertCurrencyFeign(from, to, BigDecimal.valueOf(Long.valueOf(quantity)));
		logger.info("{}", currencyConversionBean);		
		return currencyConversionBean;
		
	}
}
