package com.capco.resource.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capco.resource.exceptions.CustomerException;
import com.capco.resource.model.ResponseObject;
import com.capco.resource.model.Result;
import com.capco.resource.model.Skill;
import com.capco.resource.model.Status;
import com.capco.resource.model.UserInfo;
import com.capco.resource.repository.UserRepo;
import com.capco.resource.service.ForgetPasswordService;
import com.capco.resource.validations.RegistrationValidations;


@Service
public class ForgetPasswordServiceImpl implements ForgetPasswordService {
	
	@Autowired
	UserRepo userRepo;
	
	 Status status=new Status();

	@Override
	public ResponseEntity<ResponseObject> forgotpassword(UserInfo user) throws CustomerException, Exception {
		
		 ResponseObject object=new ResponseObject();
			 UserInfo userById = userRepo.findByEmpId(user.getEmpId());	
			 try {
			 if(userById!=null) {	 
			if(userById.getEmpId()==(user.getEmpId())) {			
				if(userById.getEmployeeEmail().equals(user.getEmployeeEmail())) {
					
					if(user.getSqQuestion().equals(userById.getSqQuestion()) && user.getSqAnswer().equals(userById.getSqAnswer())){
									
						if(user!=null) {
							if(userById.isFlag()==true) {
							
							userById.setPassword(user.getPassword());	
							
							userRepo.save(userById);
							 status.setCode(200);
						     status.setMessage("Success");
						     object.setStatus(status);
						}else {
							 status.setCode(400);
		          		     status.setMessage("This user has been removed");
		          		     object.setStatus(status);
		          		     object.setResult(null);
		          				return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
		            
						}
						}
						else {
							 status.setCode(400);
						     status.setMessage("Please Enter the details");
						     object.setStatus(status);
						     
								return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
						} 
					}else {
						 status.setCode(400);
					     status.setMessage("Security Question or answer not valid");
					     object.setStatus(status);
					     
							return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
					
					}
					
				}
					else {
					 status.setCode(400);
				     status.setMessage("Email ID may be wrong");
				     object.setStatus(status);
						return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
				}
		 			
				}
			 else {
				 status.setCode(400);
			     status.setMessage("Emp ID may be wrong");
			     object.setStatus(status);
			     
					return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
			 
			 }
			 }else {
				 status.setCode(400);
			     status.setMessage("Emp ID not registered");
			     object.setStatus(status);
			    
					return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
		
			 }
			 }
			 catch(Exception e) {
				 e.printStackTrace();
			 }
		
	    
		 return new ResponseEntity<>(object, HttpStatus.OK);
			    
	}

	@Override
	public ResponseEntity<ResponseObject> searchbyId(UserInfo user) throws CustomerException, Exception {
		Result filterResult = new Result();
		 ResponseObject object=new ResponseObject();
		    UserInfo temp = new UserInfo();
		    
		try {
			
			if(user.getEmpId()!=0 && !(user.getEmployeeEmail().isEmpty())) {
				 temp = userRepo.findByEmpIdAndEmail(user.getEmpId(), user.getEmployeeEmail());
				
			}
			else {
				if(user.getEmpId()!=0 ) {
					 temp = userRepo.findByEmpIdOrEmail(user.getEmpId(), user.getEmployeeEmail());
					
				}else if(!(user.getEmployeeEmail().isEmpty())) {
					 temp = userRepo.findByEmpIdOrEmail(user.getEmpId(), user.getEmployeeEmail());
					
				}
				else {
					 status.setCode(400);
				     status.setMessage("Any one field is required for Search");
				     object.setStatus(status);
				     filterResult.setFilterResult(null);
				      object.setResult(filterResult);
				      return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
		 
					
				}
				
			}
			
				if((temp!=null)){
	        	   
	        	
	        	 filterResult.setSqQuestion(temp.getSqQuestion());
	        	 status.setCode(200);
	                status.setMessage("Success");
	                object.setStatus(status);
	                object.setResult(filterResult);
	           	
	                   
	         }
	            		   
	               else {
	            	   status.setCode(400);
	        		     status.setMessage("No data found for Search Result");
	        		     object.setStatus(status);
	        				return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
	            	   
	               }   
			
			              
			}catch(Exception e) {
				 e.printStackTrace(); 
			}
			  
	   
	        return new ResponseEntity<>(object, HttpStatus.OK); 
	    

	}

}
