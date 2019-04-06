package com.leadspring.microservices.currencyexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leadspring.microservices.currencyexchange.model.ExchangeValue;



@Repository
public interface CurencyExchangeRepository extends JpaRepository<ExchangeValue, Long>{
	
	ExchangeValue findByFromCurrencyCodeAndToCurrencyCode(String fromCurrencyCode, String toCurrencyCode );

}
