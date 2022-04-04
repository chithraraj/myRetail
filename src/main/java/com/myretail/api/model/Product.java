package com.myretail.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

	private Integer id;
	
	private String name;
	
	@JsonProperty("current_price") 
	private currentPrice currentPrice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public currentPrice getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(currentPrice currentPrice) {
		this.currentPrice = currentPrice;
	}

}
