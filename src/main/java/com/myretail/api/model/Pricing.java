package com.myretail.api.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType.Name;
import com.datastax.driver.core.LocalDate;

@Table
public class Pricing {

	@Id	
	private String product_id;

	@CassandraType(type = Name.UDT, userTypeName = "price")
	@Column("current_price")
	private currentPrice currentPrice;

	@Column("last_updated_date")
	private Date lastUpdatedDate;

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public currentPrice getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(currentPrice currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

}
