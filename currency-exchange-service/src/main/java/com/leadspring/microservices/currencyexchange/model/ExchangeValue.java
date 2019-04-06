package com.leadspring.microservices.currencyexchange.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="currency exchange model")
@Entity
public class ExchangeValue {

	@Id
	private Long id;
	@Size(min = 3, max = 3, message="must be 3 characters")
	@ApiModelProperty(notes="From currency code must be 3 characters")
	@Column(length=3)
	private String fromCurrencyCode;
	@Size(min = 3, max = 3, message="From currency code must be 3 characters")
	@ApiModelProperty(notes="must be 3 characters")
	@Column(length=3)
	private String toCurrencyCode;
	@Column(precision=20, scale=16)
	private BigDecimal conversionMultiple;
		
	
	public ExchangeValue() {
	
	}

	public ExchangeValue(Long id, String fromCurrencyCode, String toCurrencyCode, BigDecimal conversionMultiple) {
		super();
		this.id = id;
		this.fromCurrencyCode = fromCurrencyCode;
		this.toCurrencyCode = toCurrencyCode;
		this.conversionMultiple = conversionMultiple;
	}

	
	public Long getId() {
		return id;
	}

	public String getFromCurrencyCode() {
		return fromCurrencyCode;
	}

	public String getToCurrencyCode() {
		return toCurrencyCode;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

		
}
