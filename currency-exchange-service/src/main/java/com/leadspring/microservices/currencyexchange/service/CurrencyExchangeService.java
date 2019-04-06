package com.leadspring.microservices.currencyexchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leadspring.microservices.currencyexchange.bean.ExchangeValueDTO;
import com.leadspring.microservices.currencyexchange.model.ExchangeValue;
import com.leadspring.microservices.currencyexchange.repository.CurencyExchangeRepository;

@Service
public class CurrencyExchangeService {
	@Autowired
	private CurencyExchangeRepository curencyExchangeRepository;
	
	public ExchangeValueDTO retrieveExchangeValue(String fromCurrencyCode, String toCurrencyCode) {
		ExchangeValueDTO exchangeValuedto = new ExchangeValueDTO();
		ExchangeValue exchangeValuecurency = curencyExchangeRepository.findByFromCurrencyCodeAndToCurrencyCode(fromCurrencyCode, toCurrencyCode);
		if (exchangeValuecurency != null) {
			exchangeValuedto.setFrom(exchangeValuecurency.getFromCurrencyCode());
			exchangeValuedto.setTo(exchangeValuecurency.getToCurrencyCode());
			exchangeValuedto.setId(exchangeValuecurency.getId());
			exchangeValuedto.setConversionMultiple(exchangeValuecurency.getConversionMultiple());
		}
		
		return exchangeValuedto;
	}

}
