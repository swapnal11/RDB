package com.capco.resource.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capco.resource.exceptions.CustomerException;
import com.capco.resource.model.ResponseObject;
import com.capco.resource.model.UserInfo;
import com.capco.resource.service.ForgetPasswordService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/api")
public class ForgetPasswordController {


	@Autowired
	ForgetPasswordService forgetpasswordservice;
	
	ResponseEntity response;
	
	@PostMapping(value = "/forgotpassword")
	 public ResponseEntity<String> forgotpassword(@RequestBody UserInfo user) throws Exception {
		
		response = forgetpasswordservice.forgotpassword(user);
		return response;
		
		
		}
	
	@GetMapping(value = "/searchUserById/{id}")
    public  ResponseEntity<ResponseObject> searchbyId(@PathVariable int id ) throws CustomerException, Exception {
           
			
		return forgetpasswordservice.searchbyId(id);
           
         
           
           }
	
}
