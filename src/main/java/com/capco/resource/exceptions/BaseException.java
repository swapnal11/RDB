package com.capco.resource.exceptions;

/**
 * This is a base exception class
 * 
 * @author SGPW
 *
 */
public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private final String message;

	public BaseException(String message) {
		super();
		
		this.message = message;
	}

	
	@Override
	public String getMessage() {
		return message;
	}
}
