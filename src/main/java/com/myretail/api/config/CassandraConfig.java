package com.myretail.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Value("${spring.data.cassandra.keyspaceName}")
	private String keyspaceName;

	@Value("${spring.data.cassandra.port}")
	private int port;

	@Value("${spring.data.cassandra.contact-points}")
	private String contactPoints;

	@Override
	protected String getKeyspaceName() {
		// TODO Auto-generated method stub
		return keyspaceName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getContactPoints() {
		return contactPoints;
	}

	public void setContactPoints(String contactPoints) {
		this.contactPoints = contactPoints;
	}

	public void setKeyspaceName(String keyspaceName) {
		this.keyspaceName = keyspaceName;
	}

	
	 @Override 
	 protected boolean getMetricsEnabled() { return false; }
	 

}
