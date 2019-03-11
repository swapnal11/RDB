package com.capco.resource.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capco.resource.model.FilterData;
import com.capco.resource.model.FilterResult;
import com.capco.resource.model.ResponseObject;
import com.capco.resource.model.Result;
import com.capco.resource.model.Skill;
import com.capco.resource.model.Status;
import com.capco.resource.model.UserInfo;
import com.capco.resource.repository.FilterRepo;
import com.capco.resource.repository.SkillRepo;
import com.capco.resource.repository.UserRepo;
import com.capco.resource.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	FilterRepo filterRepo;
	
	@Autowired
	SkillRepo skillRepo;
	
	@Autowired
	UserRepo userRepo;
	
	 ResponseObject object=new ResponseObject();
     Status status=new Status();
     Result result = new Result();
     
   	@Autowired(required=true)
	EntityManager entityManager;
	
	ModelMapper modelMapper = new ModelMapper();

	private Object namelist;

	@Override
	public ResponseEntity<ResponseObject> searchResult(FilterData result) {
		List<FilterResult> resultobj = new ArrayList<>();
		List<Object[]>	resultList;
		List<FilterResult> resultdata = new ArrayList<>();
		try {
			if(result.getExperience().equals("") && result.getExperienceYears()==0 )
				{
				if(result.getSkillName().isEmpty() && result.getStatus().isEmpty()) {
					
					status.setCode("404");
				     status.setMessage("Empty Data");
				     object.setStatus(status);
				     object.setFilterResult(null);
				     return new ResponseEntity<>(object, HttpStatus.OK);

				}
				 
			}
		else {
			String expdata=result.getExperience();
			int experience = result.getExperienceYears();
			 List<String> statuslist = result.getStatus();
			 List<String> namelist = result.getSkillName();	 
			if(expdata.equals("More Than")) {
				resultList=userRepo.findBySkillsSkillNameInAndExperienceYearsGreaterThanAndStatusIn(namelist,experience,statuslist);
					}
			else if(expdata.equals("Less Than")) {
				resultList=userRepo.findBySkillsSkillNameInAndExperienceYearsLessThanAndStatusIn(namelist,experience,statuslist);
					
			}else {
				resultList=userRepo.findBySkillsSkillNameInAndExperienceYearsAndStatusIn(namelist,experience,statuslist);
				}
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
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(object, HttpStatus.OK);
	

	}

	@Override
	public  ResponseEntity<ResponseObject> searchbyIdAndEmail(UserInfo userdata) {
			 FilterResult result= new FilterResult();
		try {
				 
			List<Object[]> temp = userRepo.findByEmpIdOrEmployeeEmail(userdata.getEmpId(), userdata.getEmployeeEmail());
			
			for(Object[] resultobj1:temp) {
				if(resultobj1[0]==null && resultobj1[1]==null && resultobj1[2]==null && resultobj1[3]==null && resultobj1[4]==null ) {
					 status.setCode("200");
			     status.setMessage("No Data Found for Search Result");
			     object.setStatus(status);
			     object.setFilterResult(null);
	 
			}else {
		    	
			    	  result.setEmpId((int) resultobj1[0]);
			    	  result.setEmployeeName(resultobj1[1].toString());
		    	  result.setStatus((String)resultobj1[2]);
			    	  result.setExperienceYears((int)resultobj1[3]);
			    	  result.setSkillName((String) resultobj1[4]);
			    	  status.setCode("200");
					     status.setMessage("Success");
					     object.setStatus(status);
					     object.setFilterResult(result);
		
		    	 }   }   
			}catch(Exception e) {
				 e.printStackTrace();
			}
			  
	   
		return new ResponseEntity<>(object, HttpStatus.OK);
 	    
	}

	@Override
	public ResponseEntity<ResponseObject> searchbyId(int id) {
		
		try {
	        UserInfo user = userRepo.findByEmpId(id);
	         if(user!=null && user.getEmpId()!=0) {
	        	 if( user.getEmpId()==id) {
	        		 
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
	                    }
	            		   
	            	   else {
	            		   status.setCode("400");
	            		     status.setMessage("EmpId is wrong");
	            		     object.setStatus(status);
	            			return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
	            	   }
	               }else {
	            	   status.setCode("400");
	        		     status.setMessage("No data found for EmpID");
	        		     object.setStatus(status);
	        				return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
	            	   
	               }       	 
			              
			}catch(Exception e) {
				 e.printStackTrace(); 
			}
			  
	   
	        return new ResponseEntity<>(object, HttpStatus.OK); 
	    

}

	@Override
	public ResponseEntity<ResponseObject> deleteUserbyId(UserInfo userinfo) {
		 UserInfo saveuser = new UserInfo();
		 try {
		 UserInfo userById = userRepo.findByEmpId(userinfo.getEmpId());
		
			 if( userById!=null && userById.getEmpId()!=0 ) {
				 userById.setFlag(false);
				userRepo.save(userById);
				 status.setCode("200");
			     status.setMessage("Success");
			     object.setStatus(status);
				
				}
		 else {
			 status.setCode("400");
		     status.setMessage("Error in deleting or empid may be wrong");
		     object.setStatus(status);
			return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
		 }
		}
		 catch(Exception e){
			 
			 e.printStackTrace();
		 }
		
		 return new ResponseEntity<>(object, HttpStatus.OK);
		
	}
}
