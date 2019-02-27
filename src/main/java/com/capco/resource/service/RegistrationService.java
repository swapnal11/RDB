package com.capco.resource.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capco.resource.model.UserInfo;

public interface RegistrationService {
	
	public  ResponseEntity<String> registeruser(UserInfo user);

	public  ResponseEntity<String> updateuser(UserInfo user);


	public ResponseEntity<String> verify(UserInfo login);
	 
	 public List<UserInfo> retrive();
	 
}
