package com.capco.resource.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Status {

	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	

	


	public Status() {
		// TODO Auto-generated constructor stub
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
