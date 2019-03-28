package com.capco.resource.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capco.resource.exceptions.CustomerException;
import com.capco.resource.model.ResponseObject;
import com.capco.resource.model.Skill;
import com.capco.resource.model.UploadFileResponse;
import com.capco.resource.model.UserInfo;

import com.capco.resource.service.RegistrationService;
import com.google.gson.Gson;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/api")
public class RegistrationController {
	
	ResponseEntity response;
	
	@Autowired
	RegistrationService registrationservice;
	
	@PostMapping(value = "/hrRegister")
	 public ResponseEntity<String> hrRegister(@RequestBody UserInfo user) throws Exception {
		
		response = registrationservice.hrRegister(user);
		return response;
		
		
		}
	
	@PostMapping(value = "/userRegister")
	 public ResponseEntity<String> userRegister(@RequestBody UserInfo user) throws CustomerException, Exception {
		response = registrationservice.userRegister(user);
		return response;
		
		
		
		}
	
	/*@PostMapping(value = "/updateUser",consumes =  "multipart/form-data")
	 public ResponseEntity<String> updateUser(@RequestPart("file") MultipartFile file, @RequestPart("user") String user) throws CustomerException, Exception {
		System.out.println("In update Controller"+user);
        String jsonResponseString = user;
        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(jsonResponseString, UserInfo.class);
      

		response = registrationservice.updateUser(file,userInfo);
		return response;
		
		}*/
	
	@PostMapping(value = "/updateUser",consumes =  "multipart/form-data")
	 public ResponseEntity<String> updateUser(@RequestPart("file") MultipartFile file,@RequestPart("image") MultipartFile image, @RequestPart("user") String user) throws CustomerException, Exception {
		System.out.println("In update Controller"+user);
       String jsonResponseString = user;
       Gson gson = new Gson();
       UserInfo userInfo = gson.fromJson(jsonResponseString, UserInfo.class);
     

		response = registrationservice.updateUser(file,image,userInfo);
		return response;
		
		}
	
	
	
	
	
	
	@PostMapping(value = "/login")
    public ResponseEntity<ResponseObject> verify(@RequestBody UserInfo login) {
           
            response = registrationservice.verify(login);
           
           return response;
           
           }
	
	

	
	@GetMapping(value = "/retrieveAll")
	 public ResponseEntity<List<UserInfo>> retrive() {
		
		response = registrationservice.retrive();
		return response;
		
		
		}
	
}
