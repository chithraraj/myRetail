
package com.myretail.api.test.service;

import static org.mockito.ArgumentMatchers.eq;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.myretail.api.model.Pricing;
import com.myretail.api.model.Product;
import com.myretail.api.model.currentPrice;
import com.myretail.api.repository.PricingRepository;
import com.myretail.api.service.ProductService;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

	@InjectMocks
	ProductService productService;

	@Mock
	PricingRepository mockPricingRepository;

	@Mock
	RestTemplate mockRestTemplate;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test 
	public void getProductByIdTest() throws Exception {
  
		//Mock Repository data 
		Product product = mockProduct(); 
		Pricing mockPricing = new Pricing();
		mockPricing.setProduct_id("13860428");
		mockPricing.setCurrentPrice(product.getCurrentPrice());
		Mockito.when(mockPricingRepository.findById(Mockito.anyString())).thenReturn(Optional.of(mockPricing));
  
		//Mock Redsky API response; 
		ResponseEntity<String> mockResponse = new ResponseEntity<String>("{\"data\":{\"product\":{\"tcin\":\"13860428\",\"item\":{\"product_description\":{\"title\":\"The Big Lebowski (Blu-ray)\"}}}}}" , HttpStatus.FOUND);
		Mockito.when(mockRestTemplate.getForEntity(Mockito.anyString(),eq(String.class))).thenReturn(mockResponse);
  
		Product actualProduct = productService.getProductById("13860428");
  
		//Expected Result
		Product expectedProduct = mockProduct();  
		Assertions.assertEquals(expectedProduct.getId(), actualProduct.getId());  
	}

	@Test
	public void updatePriceByIdTest() {

		Product product = mockProduct();
		Pricing mockPricing = new Pricing();
		mockPricing.setProduct_id("13860428");
		mockPricing.setCurrentPrice(product.getCurrentPrice());

		Mockito.when(mockPricingRepository.save(Mockito.any())).thenReturn(mockPricing);

		Pricing updatedPricing = productService.updatePriceById(product);

		Assertions.assertEquals(updatedPricing.getCurrentPrice().getValue(), mockPricing.getCurrentPrice().getValue());
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
