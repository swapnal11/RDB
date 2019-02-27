package com.capco.resource.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capco.resource.model.UserInfo;

import com.capco.resource.service.RegistrationService;

@RestController
@RequestMapping(value="/api")
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationservice;
	
	@PostMapping(value = "/register")
	 public ResponseEntity<String> save(@RequestBody UserInfo user) {
		
		return registrationservice.registeruser(user);
		
		
		}
	
	@PutMapping(value = "/updateuser")
	 public ResponseEntity<String> updateuser(@RequestBody UserInfo user) {
		
		return registrationservice.updateuser(user);
		
		
		}
	
	@PostMapping(value = "/verify")
	 public ResponseEntity<String> verify(@RequestBody UserInfo login) {
		
		return registrationservice.verify(login);
		
		
		}
	
	@GetMapping(value = "/retrive")
	 public List<UserInfo> retrive() {
		
		return registrationservice.retrive();
		
		
		}
	
}
