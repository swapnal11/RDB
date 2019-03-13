package com.capco.resource.exceptions;

/**
 * This is service level exception class
 * @author SGPW
 *
 */
public class CustomerException extends BaseException {
	private static final long serialVersionUID = 1L;

	public CustomerException(int code) {
		super(code);
	}
}
