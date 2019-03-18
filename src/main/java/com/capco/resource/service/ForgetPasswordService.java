package com.capco.resource.service;

import org.springframework.http.ResponseEntity;

import com.capco.resource.exceptions.CustomerException;
import com.capco.resource.model.ResponseObject;
import com.capco.resource.model.UserInfo;

public interface ForgetPasswordService {
	
	public  ResponseEntity<ResponseObject> forgotpassword(UserInfo user) throws CustomerException, Exception;

	 public  ResponseEntity<ResponseObject> searchbyId(UserInfo user) throws CustomerException, Exception;

}
