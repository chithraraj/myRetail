package com.myretail.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.api.exception.CustomResponse;
import com.myretail.api.exception.ProductMisMatchException;
import com.myretail.api.exception.ProductNotFoundException;
import com.myretail.api.exception.UpdatePriceException;
import com.myretail.api.model.Pricing;
import com.myretail.api.model.Product;
import com.myretail.api.service.ProductService;

@RestController
@RequestMapping(value = "/v1/products")
public class ProductController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductInfo(@PathVariable("id") String productId) {

		logger.info("getProductInfo for  " + productId);
		Product product = null;
		try {
			product = productService.getProductById(productId);
		} catch (Exception e) {
			logger.error("Product Not Found Exception  " + e);
			throw new ProductNotFoundException();
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<CustomResponse> UpdatePrice(@RequestBody Product product,
			@PathVariable("id") String productId) {
		
		Pricing updatedPricing;
		
		if (product!=null && product.getId() != null && !(product.getId().toString().equalsIgnoreCase(productId))) {
			throw new ProductMisMatchException();
		}
		try {
			updatedPricing= productService.updatePriceById(product);
		} catch (Exception e) {
			logger.error("Error Updating Product Price for "+productId+" "+ e);
			throw new UpdatePriceException();
		}
		return new ResponseEntity<CustomResponse>(
				new CustomResponse("Product price has been updated for "+updatedPricing.getProduct_id()), HttpStatus.OK);
	}

}