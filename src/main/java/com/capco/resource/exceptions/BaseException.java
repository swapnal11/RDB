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
	
	private final int code;

	private static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	
	public BaseException(int code) {
		super();
		this.code = code;
	
	}

	public int getCode() {
		return code;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
