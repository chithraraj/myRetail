package com.myretail.api.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import com.myretail.api.model.Pricing;

public interface PricingRepository extends CassandraRepository<Pricing, String> {
		
}
