package com.leadspring.microservices.currencyconversion.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.leadspring.microservices.currencyconversion.bean.CurrencyConversionBean;

@Service

/**Calling currency-exchange-service directly Connection through feign client
 *@FeignClient(name="currency-exchange-service", url="http://localhost:8000")
**/

/**
 * Calling currency-exchange-service through Eureka Naming Server
 *@FeignClient(name="currency-exchange-service")
 *@RibbonClient(name="currency-exchange-service")
 */

//Calling currency-exchange-service through Zuul API gateway
@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyConversionServiceProxy {
	
	/**
	 * Calling through feign client and Eureka Naming Server
	 *@GetMapping("/currency-exchange/jpa/from/{from}/to/{to}")
	 * public CurrencyConversionBean retrieveJpaExchangeValue(@PathVariable String from, @PathVariable String to); 
	 */
	
	
	//Calling through Zuul Api Gateway
	@GetMapping("/currency-exchange-service/currency-exchange/jpa/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveJpaExchangeValue(@PathVariable String from, @PathVariable String to); 
		
}
