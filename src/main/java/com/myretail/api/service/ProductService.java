package com.myretail.api.service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.api.model.Pricing;
import com.myretail.api.model.Product;
import com.myretail.api.repository.*;

@Service
public class ProductService {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String DATA ="data";
	private final String PRODUCT ="product";
	private final String ITEM ="item";
	private final String PRODUCT_DESC ="product_description";
	private final String TITLE ="title";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private PricingRepository pricingRepository;
	
	 @Value("${redsky.api.url}")
	 private String redskyurl;
	 
	 @Value("${redsky.api.key}")
	 private String redskyKey;
	
	
	public Product getProductById(String productId) throws JsonMappingException, JsonProcessingException{
		
		//Retrieve Pricing details from Data store		
		Optional<Pricing> pricing = pricingRepository.findById(productId);
		
		//Retrieve Product name from RedSky API
		String name = getTitleForProduct(productId);
		
		Product product = new Product();
		product.setName(name);
		product.setId(Integer.valueOf(pricing.get().getProduct_id()));
		product.setCurrentPrice(pricing.get().getCurrentPrice()); 
		return product;
		
	}
	
	private String getTitleForProduct(String productId) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();		
		ResponseEntity<String> response= restTemplate.getForEntity(redskyurl+"?key="+redskyKey+"&tcin="+productId, String.class);
		Map<String, Map> infoMap = mapper.readValue(response.getBody(), Map.class);
		Map<String,Map> dataMap = infoMap.get(DATA);
		Map<String,Map> productMap = dataMap.get(PRODUCT);
        Map<String,Map> itemMap = productMap.get(ITEM);
        Map<String,String> prodDescMap = itemMap.get((PRODUCT_DESC));
        
		return prodDescMap.get(TITLE);
	}
	
	public Pricing updatePriceById(Product product) {
		
		//
		Pricing pricing = new Pricing();		
		pricing.setLastUpdatedDate(new Date()); 
		pricing.setCurrentPrice(product.getCurrentPrice());
		pricing.setProduct_id(product.getId().toString());
		
		Pricing updatedPricing = pricingRepository.save(pricing);
	
		return updatedPricing;
	}
}
