package com.capco.resource.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.capco.resource.exceptions.CustomerException;
import com.capco.resource.model.FilterResult;
import com.capco.resource.model.ResponseObject;
import com.capco.resource.model.Result;
import com.capco.resource.model.Skill;
import com.capco.resource.model.Status;
import com.capco.resource.model.UserInfo;
import com.capco.resource.repository.SkillRepo;
import com.capco.resource.repository.UserRepo;
import com.capco.resource.service.RegistrationService;
import com.capco.resource.validations.RegistrationValidations;



@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	SkillRepo skillRepo;
	 ResponseObject object=new ResponseObject();
     Status status=new Status();
     Result result = new Result();
     FilterResult filterResult= new FilterResult();
   

	@Override
	public ResponseEntity<ResponseObject> hrRegister(UserInfo userinfo) throws CustomerException {

	 UserInfo saveuser = new UserInfo();
		RegistrationValidations regValidations = new RegistrationValidations();
	      
	 try {
	 UserInfo userById = userRepo.findByEmpId(userinfo.getEmpId());
	 if(userById!=null) {
			if(userById.getEmpId()==(userinfo.getEmpId())) {
			 status.setCode("400");
		     status.setMessage("EmpId is already registered");
		     object.setStatus(status);
		   
				return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);	
		 }
	 }
	 
	 else {
		 if( userinfo!=null && userinfo.getEmpId()!=0 ) {
			saveuser.setDesignation(userinfo.getDesignation());
			saveuser.setEmpId(userinfo.getEmpId());
			saveuser.setEmployeeEmail(userinfo.getEmployeeEmail());
			saveuser.setStatus(userinfo.getStatus());
			saveuser.setProjectManager(userinfo.getProjectManager());
			saveuser.setEmployeeName(userinfo.getEmployeeName());
			userRepo.save(saveuser);
			 status.setCode("200");
		     status.setMessage("Success");
		     object.setStatus(status);
			
			}
	 else {
		 status.setCode("400");
	     status.setMessage("Please Enter the details");
	     object.setStatus(status);
	  
			return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
	 }
	}
	 }catch(Exception e){
		 
		 e.printStackTrace();
	 }
	
	 return new ResponseEntity<>(object, HttpStatus.OK);
	 	}	
	
	
	public ResponseEntity<ResponseObject> verify(UserInfo login) {
		
		try {
        UserInfo user = userRepo.findByEmpId(login.getEmpId());
         if(user.isFlag()==true) {
         if(user!=null && user.getEmpId()!=0) {
        	 if( user.getEmpId()==(login.getEmpId())) {
        		 
               if(user.getPassword().equals(login.getPassword())) {
            	   result.setDesignation(user.getDesignation());
                   result.setEmpId(user.getEmpId());
                   result.setEmployeeEmail(user.getEmployeeEmail());
                   result.setStatus(user.getStatus());
                   result.setProjectManager(user.getProjectManager());
                   result.setEmployeeName(user.getEmployeeName());
                   result.setExperienceYears(user.getExperienceYears());
                   result.setExperienceMonths(user.getExperienceMonths());
                   List<Skill> skills= new ArrayList<>();
           		 List<Skill> skilldetails = user.getSkills();
           		 for(Skill skillinfo:skilldetails) {
           		Skill skilldata = new Skill();
           		skilldata.setSkillName(skillinfo.getSkillName());
           		skilldata.setSkillExperience(skillinfo.getSkillExperience());
           		skills.add(skilldata);
           		status.setCode("200");
                status.setMessage("Success");
                object.setStatus(status);
                object.setResult(result);
           		}
                    result.setSkills(skills);
                    }else {
                    	  status.setCode("400");
             		     status.setMessage("Password is wrong");
             		     object.setStatus(status);
             		     object.setResult(null);
             		     
             			return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
             
                    }
            		   
            	   }else {
            		   status.setCode("400");
            		     status.setMessage("EmpId is wrong");
            		     object.setStatus(status);
            		     object.setResult(null);
            			return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
            	   }
               }else {
            	   status.setCode("400");
        		     status.setMessage("EmpId is wrong");
        		     object.setStatus(status);
        		     object.setResult(null);
        				return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
            	   
               }       	 
        }else {
        	  status.setCode("400");
 		     status.setMessage("User not found");
 		     object.setStatus(status);
 		     object.setResult(null);
 				return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
    
        }         
		}catch(Exception e) {
			 e.printStackTrace();
		}
		  
   
        return new ResponseEntity<>(object, HttpStatus.OK); 
      }


	@Override
	public ResponseEntity<ResponseObject> retrive() {
		
		
		
		List<FilterResult> resultobj = new ArrayList<>();
		try {
			List<Object[]> resultList = userRepo.findAllUser();
		if(resultList!=null) {
				  for(Object[] resultobj1:resultList) {
						if(resultobj1[0]==null && resultobj1[1]==null && resultobj1[2]==null && resultobj1[3]==null && resultobj1[4]==null ) {
							 status.setCode("200");
					     status.setMessage("No Data Found for Search Result");
					     object.setStatus(status);
					     object.setFilterResult(null);

					}else {
					  List<String> skillList= new ArrayList<>(); 
					  FilterResult temp= new FilterResult();
					  temp.setEmpId((int) resultobj1[0]);
					  temp.setEmployeeName(resultobj1[1].toString());
					  temp.setExperienceYears((int)resultobj1[2]);
					  temp.setSkillName((String) resultobj1[3]);
					  skillList.add((String) resultobj1[3]);
					  temp.setStatus(resultobj1[4].toString());
					  resultobj.add(temp);
					  status.setCode("200");
					     status.setMessage("Success");
					     object.setStatus(status);
					     object.setFilterlist((resultobj));

				  }  
				  }	
						}else {
							 status.setCode("200");
						     status.setMessage("No Data Found for Search Result");
						     object.setStatus(status);
						     object.setFilterResult(null);

						}
						
		}catch(Exception e) {
			 e.printStackTrace();
		}
		
		return new ResponseEntity<>(object, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<ResponseObject> userRegister(UserInfo user) {
			 UserInfo userById = userRepo.findByEmpId(user.getEmpId());	
			 try {
			 if(userById!=null) {	 
			if(userById.getEmpId()==(user.getEmpId())) {			
				if(userById.getEmployeeEmail().equals(user.getEmployeeEmail())) {
					
					if(user.getPassword().equals("")) {
						status.setCode("400");
					     status.setMessage("Password not set");
					     object.setStatus(status);
					     object.setResult(null);
							return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
				
					}
					if(userById.getPassword()!=null  ) {
						status.setCode("400");
					     status.setMessage("Account already exist");
					     object.setStatus(status);
					     object.setResult(null);
							return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
				
					}else {
						
						if(user!=null) {
							
							userById.setPassword(user.getPassword());	
							userById.setEmployeeName(user.getEmployeeName());
							userById.setExperienceMonths(user.getExperienceMonths());
							userById.setExperienceYears(user.getExperienceYears());
							List<Skill> skills = new ArrayList<>();
							List<Skill> skilldata = user.getSkills();
							for(Skill userskill:skilldata) {
								Skill skill = new Skill();
								skill.setUserInfo(userById);
								skill.setSkillExperience(userskill.getSkillExperience());
								skill.setSkillName(userskill.getSkillName());
								skills.add(skill);
							}
							skillRepo.saveAll(skills);				
							userRepo.save(userById);
							 status.setCode("200");
						     status.setMessage("Success");
						     object.setStatus(status);
						}
						else {
							 status.setCode("400");
						     status.setMessage("Please Enter the details");
						     object.setStatus(status);
						     
								return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
						} 
					}
				
						
					
				}else {
					 status.setCode("400");
				     status.setMessage("Email ID may be wrong");
				     object.setStatus(status);
						return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
				}
		 			
				}
			 else {
				 status.setCode("400");
			     status.setMessage("Emp ID may be wrong");
			     object.setStatus(status);
			     
					return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
			 
			 }
			 }else {
				 status.setCode("400");
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
}

