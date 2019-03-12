package com.capco.resource.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capco.resource.exceptions.CustomerException;
import com.capco.resource.model.ResponseObject;
import com.capco.resource.model.Skill;
import com.capco.resource.model.UserInfo;

import com.capco.resource.service.RegistrationService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/api")
public class RegistrationController {
	
	ResponseEntity response;
	
	@Autowired
	RegistrationService registrationservice;
	
	@PostMapping(value = "/hrRegister")
	 public ResponseEntity<String> hrRegister(@RequestBody UserInfo user) throws CustomerException {
		
		response = registrationservice.hrRegister(user);
		return response;
		
		
		}
	
	@PostMapping(value = "/userRegister")
	 public ResponseEntity<String> userRegister(@RequestBody UserInfo user) {
		response = registrationservice.userRegister(user);
		return response;
		
		
		
		}
	
	@PostMapping(value = "/login")
    public ResponseEntity<ResponseObject> verify(@RequestBody UserInfo login) {
           
            response = registrationservice.verify(login);
           
           return response;
           
           }
	
	

	
	@GetMapping(value = "/retriveAll")
	 public ResponseEntity<List<UserInfo>> retrive() {
		
		response = registrationservice.retrive();
		return response;
		
		
		}
	
}
