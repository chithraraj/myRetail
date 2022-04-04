package com.myretail.api.test.controller;

import static org.mockito.ArgumentMatchers.eq;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.myretail.api.controller.ProductController;
import com.myretail.api.exception.CustomResponse;
import com.myretail.api.exception.ProductMisMatchException;
import com.myretail.api.exception.ProductNotFoundException;
import com.myretail.api.exception.UpdatePriceException;
import com.myretail.api.model.Pricing;
import com.myretail.api.model.Product;
import com.myretail.api.model.currentPrice;
import com.myretail.api.service.ProductService;

@RunWith(SpringRunner.class)
public class ProductControllerTest {

	@InjectMocks
	ProductController productController;
	
	@Mock
	ProductService mockProductService;

	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test 
	public void getProductInfoTest() throws Exception {
		
		//Mock product
		Product mockProduct = mockProduct();  
		Mockito.when(mockProductService.getProductById(Mockito.anyString())).thenReturn(mockProduct);
		
		ResponseEntity<Product> getResponse = productController.getProductInfo("13860428");
		
		Assertions.assertEquals(getResponse.getBody(), mockProduct);
		
		
	}
	

	@Test
	public void getProductInfo_NotFoundTest() throws JsonMappingException, JsonProcessingException{
		
		Mockito.when(mockProductService.getProductById(eq("123"))).thenThrow(new ProductNotFoundException());
		
		Assertions.assertThrows(ProductNotFoundException.class, () -> {
			productController.getProductInfo("123");
		}, "ProductNotFoundException was expected");
		
		
	}

	@Test
	public void UpdatePriceTest() {
		
		Product mockProduct = mockProduct();  
		Pricing mockPricing = new Pricing();
		mockPricing.setProduct_id("13860428");
		mockPricing.setCurrentPrice(mockProduct.getCurrentPrice());

		Mockito.when(mockProductService.updatePriceById(Mockito.any())).thenReturn(mockPricing);
		
		ResponseEntity<CustomResponse> putResponse = productController.UpdatePrice(mockProduct, "13860428");
		
		Assertions.assertEquals(putResponse.getBody().getMessage(), "Product price has been updated for 13860428");
		
	}
	
	@Test
	public void UpdatePrice_MisMatchExceptionTest() {
		
		Product mockProduct = mockProduct();  
		
		Assertions.assertThrows(ProductMisMatchException.class, () -> {
			productController.UpdatePrice(mockProduct, "123");
		}, "ProductMisMatchException was expected");
		
	}
	
	@Test
	public void UpdatePriceExceptionTest() {
		
		Product mockProduct = mockProduct();  
		Pricing mockPricing = new Pricing();
		mockPricing.setProduct_id("13860428");
		mockPricing.setCurrentPrice(mockProduct.getCurrentPrice());

		Mockito.when(mockProductService.updatePriceById(Mockito.any())).thenThrow(new UpdatePriceException());
		
		Assertions.assertThrows(UpdatePriceException.class, () -> {
			productController.UpdatePrice(mockProduct, "13860428");
		}, "UpdatePriceException was expected");
		
	}

	private Product mockProduct() {

		Product product = new Product();
		product.setName("The Big Lebowski (Blu-ray)");
		product.setId(13860428);
		currentPrice currentPrice = new currentPrice();
		currentPrice.setCurrencyCode("USD");
		currentPrice.setValue(12.50);
		product.setCurrentPrice(currentPrice);
		return product;
	}

}
