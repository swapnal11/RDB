package com.capco.resource.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capco.resource.model.Skill;
import com.capco.resource.model.UserInfo;
import com.capco.resource.repository.SkillRepo;
import com.capco.resource.repository.UserRepo;
import com.capco.resource.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	SkillRepo skillRepo;
	
	


	@Override
	public ResponseEntity<String> registeruser(UserInfo userinfo) {

		UserInfo saveuser = new UserInfo();
	 UserInfo userById = userRepo.findByEmpId(userinfo.getEmpId());
	 if(userById!=null) {
		 
		if(userById.getEmpId().equals(userinfo.getEmpId())) {
		return new ResponseEntity<>("EmpId Already Exists", HttpStatus.BAD_REQUEST);
		 }
		 }
	 else {
		 if(userinfo!=null) {
			saveuser.setDesignation(userinfo.getDesignation());
			saveuser.setEmpId(userinfo.getEmpId());
			saveuser.setEmployeeEmail(userinfo.getEmployeeEmail());
			saveuser.setStatus(userinfo.getStatus());
			saveuser.setProjectManager(userinfo.getProjectManager());
			saveuser.setEmployeeName(userinfo.getEmployeeName());
			userRepo.save(saveuser);
			}
	 else 
			return new ResponseEntity<>("Please Enter your details", HttpStatus.BAD_REQUEST);
		
	}
	 return new ResponseEntity<>("You have Sucessfully Signup. Please login here", HttpStatus.OK);
		
	}	
	
	
	public ResponseEntity<String> verify(UserInfo login) {
		UserInfo user = userRepo.findByEmpId(login.getEmpId());
		 
		 if(user!=null) {
			 if(user.getPassword().equals(login.getPassword())) {
			 }
			 else
				  return new ResponseEntity<String>("Bad Credentials",HttpStatus.BAD_REQUEST) ;
		 }
		 
		  return new ResponseEntity<String>("Login Successfull",HttpStatus.OK) ;
	}


	@Override
	public List<UserInfo> retrive() {
		List<UserInfo> userlist = new  ArrayList();
		List<UserInfo> list = userRepo.findAll();
		
		for(UserInfo userdetails:list) {
			UserInfo user= new UserInfo();
			user.setDesignation(userdetails.getDesignation());
			user.setEmpId(userdetails.getEmpId());
			user.setEmployeeEmail(userdetails.getEmployeeEmail());
			user.setEmployeeName(userdetails.getEmployeeName());
			userlist.add(user);
		}
		return userlist;
	}


	@Override
	public ResponseEntity<String> updateuser(UserInfo user) {
			 System.out.println("USer---------------"+user.toString());
		 UserInfo userById = userRepo.findByEmpId(user.getEmpId());	 
		 System.out.println("userById---------------"+userById);
		 
		 if(userById!=null) {
			 
			if(userById.getEmpId().equals(user.getEmpId())  && userById.getEmployeeEmail().equals(user.getEmployeeEmail())) {
				if(user!=null) {
					userById.setPassword(user.getPassword());
					userById.setEmployeeName(user.getEmployeeName());
					userById.setExperience(user.getExperience());
					List<Skill> skills = new ArrayList<>();
					List<Skill> skilldata = user.getSkills();
					for(Skill userskill:skilldata) {
						Skill skill = new Skill();
						skill.setUserInfo(userById);
						skill.setExperience(userskill.getExperience());
						skill.setSkillName(userskill.getSkillName());
						skills.add(skill);
					}
					
					//userById.setSkills(skills);
					skillRepo.saveAll(skills);			
					
					userRepo.save(userById);
				
				}
				else {
					return new ResponseEntity<>("Please Enter the data", HttpStatus.BAD_REQUEST);
					}
				}
		 
		 else 				
				return new ResponseEntity<>("Incorrect Details", HttpStatus.BAD_REQUEST);
				}
		 return new ResponseEntity<>("You have Sucessfully Signup. Please login here", HttpStatus.OK);
		
	}		
}

