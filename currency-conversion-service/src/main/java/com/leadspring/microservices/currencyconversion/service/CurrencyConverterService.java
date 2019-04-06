package com.leadspring.microservices.currencyconversion.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.leadspring.microservices.currencyconversion.bean.CurrencyConversionBean;

@Service
public class CurrencyConverterService {

	@Autowired
	private CurrencyConversionServiceProxy currencyConversionServiceProxy;

	public CurrencyConversionBean convertCurrency(String fromCurrency, String toCurrency, BigDecimal quantity) {

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", fromCurrency);
		uriVariables.put("to", toCurrency);
		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/jpa/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariables);
		CurrencyConversionBean currencyConversionBean = responseEntity.getBody();
		currencyConversionBean.setQuantity(quantity);
		currencyConversionBean.setConvertedAmount(quantity.multiply(currencyConversionBean.getConversionMultiple()));
		currencyConversionBean
				.setConvertedAmount(currencyConversionBean.getConvertedAmount().setScale(2, RoundingMode.FLOOR));
		return currencyConversionBean;
	}

	public CurrencyConversionBean convertCurrencyFeign(String fromCurrency, String toCurrency, BigDecimal quantity) {

		CurrencyConversionBean currencyConversionBean = currencyConversionServiceProxy
				.retrieveJpaExchangeValue(fromCurrency, toCurrency);

		// CurrencyConversionBean currencyConversionBean = responseEntity.getBody();
		currencyConversionBean.setQuantity(quantity);
		currencyConversionBean.setConvertedAmount(quantity.multiply(currencyConversionBean.getConversionMultiple()));
		currencyConversionBean
				.setConvertedAmount(currencyConversionBean.getConvertedAmount().setScale(2, RoundingMode.FLOOR));
		return currencyConversionBean;
	}

}
