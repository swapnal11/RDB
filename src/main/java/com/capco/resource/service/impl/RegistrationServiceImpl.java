package com.capco.resource.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.capco.resource.exceptions.CustomerException;
import com.capco.resource.exceptions.FileStorageException;
import com.capco.resource.exceptions.MyFileNotFoundException;
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
	
     Status status=new Status();

	@Override
	public ResponseEntity<ResponseObject> hrRegister(UserInfo userinfo) throws CustomerException ,Exception{
		
		RegistrationValidations.lengthValidationForName(userinfo.getDesignation());
		RegistrationValidations.lengthValidationForName(userinfo.getEmployeeName());
		RegistrationValidations.lengthValidationForName(userinfo.getProjectManager());
		RegistrationValidations.lengthValidationForName(userinfo.getStatus());
		RegistrationValidations.isEmailValid(userinfo.getEmployeeEmail());
		RegistrationValidations.lengthValidationForNumber(userinfo.getEmpId());
		
		
		
		
	     Result result = new Result();
		 ResponseObject object=new ResponseObject();
	 UserInfo saveuser = new UserInfo();
		RegistrationValidations regValidations = new RegistrationValidations();
	      
	 try {
	 UserInfo userById = userRepo.findByEmpId(userinfo.getEmpId());
	 if(userById!=null) {
			if(userById.getEmpId()==(userinfo.getEmpId())) {
			 status.setCode(400);
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
			 status.setCode(200);
		     status.setMessage("Success");
		     object.setStatus(status);
			
			}
	 else {
		 status.setCode(400);
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
	
	
	public ResponseEntity<ResponseObject> verify(UserInfo login,HttpServletResponse response) {
	     Result result = new Result();
		 ResponseObject object=new ResponseObject();
		
		try {
        UserInfo user = userRepo.findByEmpId(login.getEmpId());
         
         if(user!=null && user.getEmpId()!=0) {
        	 
        	 if( user.getEmpId()==(login.getEmpId())) {
        		 
               if(user.getPassword().equals(login.getPassword())) {
            	   
            	  if(user.isFlag()==true) {
            	   result.setDesignation(user.getDesignation());
                   result.setEmpId(user.getEmpId());
                   result.setEmployeeEmail(user.getEmployeeEmail());
                   result.setStatus(user.getStatus());
                   result.setProjectManager(user.getProjectManager());
                   result.setEmployeeName(user.getEmployeeName());
                   result.setExperienceYears(user.getExperienceYears());
                   result.setExperienceMonths(user.getExperienceMonths());
                   result.setFileName(user.getFileName());
                   byte[] imagedata= user.getData();
                 result.setImagedata(imagedata);
                   List<Skill> skills= new ArrayList<>();
           		 List<Skill> skilldetails = user.getSkills();
           		 for(Skill skillinfo:skilldetails) {
           		Skill skilldata = new Skill();
           		skilldata.setSkillName(skillinfo.getSkillName());
           		skilldata.setSkillExperience(skillinfo.getSkillExperience());
           		skills.add(skilldata);
           		status.setCode(200);
                status.setMessage("Success");
                object.setStatus(status);
                object.setResult(result);
                
           		}
                    result.setSkills(skills);
            	  }else {
                	  status.setCode(400);
          		     status.setMessage("This user has been removed");
          		     object.setStatus(status);
          		     object.setResult(null);
          				return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
             
                 }  
                    }else {
                    	  status.setCode(400);
             		     status.setMessage("Password is wrong");
             		     object.setStatus(status);
             		     object.setResult(null);
             		     
             			return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
             
                    }
            		   
            	   }else {
            		   status.setCode(400);
            		     status.setMessage("EmpId is wrong");
            		     object.setStatus(status);
            		     object.setResult(null);
            			return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
            	   }
               }else {
            	   status.setCode(400);
        		     status.setMessage("EmpId is wrong");
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
	     Result result = new Result();
		 ResponseObject object=new ResponseObject();
		
		
		List<FilterResult> resultobj = new ArrayList<>();
		try {
			List<Object[]> resultList = userRepo.findAllUser();
		if(resultList!=null) {
				  for(Object[] resultobj1:resultList) {
						if(resultobj1[0]==null && resultobj1[1]==null && resultobj1[2]==null && resultobj1[3]==null && resultobj1[4]==null ) {
							 status.setCode(200);
					     status.setMessage("No Data Found for Search Result");
					     object.setStatus(status);
					     result.setFilterResult(null);
					      object.setResult(result);
					      

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
					  status.setCode(200);
					     status.setMessage("Success");
					     object.setStatus(status);
					     result.setFilterlist(resultobj);
					      object.setResult(result);
					
				  }  
				  }	
						}else {
							 status.setCode(200);
						     status.setMessage("No Data Found for Search Result");
						     object.setStatus(status);
						     result.setFilterResult(null);
						      object.setResult(result);
						
						}
						
		}catch(Exception e) {
			 e.printStackTrace();
		}
		
		return new ResponseEntity<>(object, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<ResponseObject> userRegister(UserInfo user) throws CustomerException,Exception {
		
	/*	RegistrationValidations.lengthValidationForName(user.getEmployeeName());
		RegistrationValidations.lengthValidationForNumber(user.getExperienceMonths());
		RegistrationValidations.lengthValidationForNumber(user.getExperienceYears());
		for(Skill userskill:user.getSkills()) {
			RegistrationValidations.lengthValidationForName(userskill.getSkillName());
			RegistrationValidations.lengthValidationForNumber(userskill.getSkillExperience());		
		}
		*/
		 ResponseObject object=new ResponseObject();
			 UserInfo userById = userRepo.findByEmpId(user.getEmpId());	
			 try {
			 if(userById!=null) {	 
			if(userById.getEmpId()==(user.getEmpId())) {			
				if(userById.getEmployeeEmail().equals(user.getEmployeeEmail())) {
					
					if(user.getPassword().equals("")) {
						status.setCode(400);
					     status.setMessage("Password not set");
					     object.setStatus(status);
					     object.setResult(null);
							return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
				
					}
					if(userById.getPassword()!=null  ) {
						status.setCode(400);
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
							userById.setSqQuestion(user.getSqQuestion());
							userById.setSqAnswer(user.getSqAnswer());
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
							status.setCode(200);
						    status.setMessage("Success");
						     object.setStatus(status);
						}
						else {
							 status.setCode(400);
						     status.setMessage("Please Enter the details");
						     object.setStatus(status);
							return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
						} 
					}
				
						
					
				}else {
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
	public ResponseEntity<ResponseObject> updateUser(MultipartFile file,MultipartFile image,UserInfo user) throws CustomerException, Exception {
		/*RegistrationValidations.lengthValidationForName(user.getEmployeeName());
		RegistrationValidations.lengthValidationForNumber(user.getExperienceMonths());
		RegistrationValidations.lengthValidationForNumber(user.getExperienceYears());
		for(Skill userskill:user.getSkills()) {
			RegistrationValidations.lengthValidationForName(userskill.getSkillName());
			RegistrationValidations.lengthValidationForNumber(userskill.getSkillExperience());		
		}
		*/
		 ResponseObject object=new ResponseObject();
			 UserInfo userById = userRepo.findByEmpId(user.getEmpId());	
			 String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			 String imageName = StringUtils.cleanPath(image.getOriginalFilename());
				
			 UserInfo objUser= new UserInfo();
			 try {
			 if(userById!=null) {	 
			if(userById.getEmpId()==(user.getEmpId())) {			
				if(userById.getEmployeeEmail().equals(user.getEmployeeEmail())) {
						if(userById.isFlag()==true) {
							userById.setEmployeeName(user.getEmployeeName());
							userById.setExperienceMonths(user.getExperienceMonths());
							userById.setExperienceYears(user.getExperienceYears());
							userById.setDesignation(user.getDesignation());
							userById.setStatus(user.getStatus());
							try {
							 if(fileName.contains("..")) {
					                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
					            }
					            userById.setFileName(fileName);
					            userById.setFileType(file.getContentType());
					            userById.setData(file.getBytes());
					            userById.setImageName(imageName);
					           userById.setImageType(image.getContentType());
					           userById.setImagedata(image.getBytes());
					             objUser = userRepo.save(userById);
								
							}catch(IOException ex) {
								throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
							     
							}
								List<Skill> skills = new ArrayList<>();
							List<Skill> skilldata = objUser.getSkills();
							System.out.println("skilldata"+skilldata);
							for(Skill userskill:skilldata) {
							  skillRepo.deleteByUserInfoEmpId((userskill.getUserInfo().getEmpId()));			
						}
							List<Skill> skillDataToUpdate = user.getSkills();
							for(Skill obj:skillDataToUpdate) {
								Skill skill = new Skill();
								skill.setUserInfo(userById);
								skill.setSkillExperience(obj.getSkillExperience());
								skill.setSkillName(obj.getSkillName());
								skills.add(skill);
							}
							skillRepo.saveAll(skills);				
							 status.setCode(200);
						     status.setMessage("Success");
						     object.setStatus(status);
						
				}else {
              	  status.setCode(400);
        		     status.setMessage("Can't Update , User has been removed from Database");
        		     object.setStatus(status);
        		     object.setResult(null);
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
	
	
	 public UserInfo getFile(String fileId) {
		 int id = Integer.parseInt(fileId);
		 try {
	        return userRepo.findByEmpId(id);
	             
	    }catch(Exception e) {
	    	throw new MyFileNotFoundException("File not found with id " + fileId);
	    	}
	    }


	
}

