package com.capco.resource.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.capco.resource.exceptions.CustomerException;
import com.capco.resource.model.ResponseObject;
import com.capco.resource.model.UserInfo;

public interface RegistrationService {
	
	public  ResponseEntity<ResponseObject> hrRegister(UserInfo user) throws CustomerException, Exception;

	public  ResponseEntity<ResponseObject> userRegister(UserInfo user) throws CustomerException, Exception;

	public ResponseEntity<ResponseObject> verify(UserInfo login);
	 
	 public ResponseEntity<ResponseObject> retrive();

	public  ResponseEntity<ResponseObject> updateUser(MultipartFile file,UserInfo user) throws CustomerException, Exception;;
	 
	 
	 
	 
}
