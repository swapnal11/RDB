package com.capco.resource.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capco.resource.model.ResponseObject;
import com.capco.resource.model.UserInfo;

public interface RegistrationService {
	
	public  ResponseEntity<ResponseObject> hrRegister(UserInfo user);

	public  ResponseEntity<ResponseObject> userRegister(UserInfo user);


	public ResponseEntity<ResponseObject> verify(UserInfo login);
	 
	 public ResponseEntity<List<UserInfo>> retrive();
	 
}
