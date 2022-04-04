package com.myretail.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_MODIFIED, reason = "Error Updating Product Pricing.")
public class UpdatePriceException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public UpdatePriceException() {
		
	}

}
