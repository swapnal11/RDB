package com.capco.resource.exceptions;

import org.springframework.http.HttpStatus;

/**
 * This is a base exception class
 * 
 * @author SGPW
 *
 */
public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private final String message;

	private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	
	public BaseException(String message) {
		super();
		
		this.message = message;
	}

	
	@Override
	public String getMessage() {
		return message;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
