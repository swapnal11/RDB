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
	
     Status status=new Status();
     
     
   	@Autowired(required=true)
	EntityManager entityManager;
	
	ModelMapper modelMapper = new ModelMapper();

	private Object namelist;

	@Override
	public ResponseEntity<ResponseObject> searchResult(FilterData result) {
		Result filterResult = new Result();
		 ResponseObject object=new ResponseObject();
		List<FilterResult> resultobj = new ArrayList<>();
		List<Object[]>	resultList;
		List<FilterResult> resultdata = new ArrayList<>();
		try {
			if((result.getExperience().equals("") && result.getExperienceYears()==0) && (result.getSkillName().isEmpty() && result.getStatus().isEmpty())) {
				List<Object[]> resultList1 = userRepo.findAllUser();
				List<FilterResult> resultobj2 = new ArrayList<>();
				for(Object[] resultobj1:resultList1) {
				 List<String> skillList= new ArrayList<>(); 
				  FilterResult temp= new FilterResult();
				  temp.setEmpId((int) resultobj1[0]);
				  temp.setEmployeeName(resultobj1[1].toString());
				  temp.setExperienceYears((int)resultobj1[2]);
				  temp.setSkillName((String) resultobj1[3]);
				  temp.setStatus((String)resultobj1[4]);
				  skillList.add((String) resultobj1[3]);
				  temp.setStatus(resultobj1[4].toString());
				  resultobj2.add(temp);
				  status.setCode(200);
				     status.setMessage("Success");
				     object.setStatus(status);
				     filterResult.setFilterlist(resultobj2);
				      object.setResult(filterResult);
				    
				   //  object.setFilterlist((resultobj2));
				     
				}
			
			}
		else {
			String expdata=result.getExperience();
			int experience = result.getExperienceYears();
			 List<String> statuslist = result.getStatus();
			 List<String> namelist = result.getSkillName();	 
			if(expdata.equals("Equal To") ) {
				if(statuslist.isEmpty() && namelist.isEmpty()) {
					statuslist.add("");
					namelist.add("");
				
				resultList=userRepo.findByExperienceYearsEqualTo(experience);
					}
				else if(namelist.isEmpty()) {
					namelist.add("");
					resultList=userRepo.findByExperienceYearsAndStatusIn(experience,statuslist);
					
					}
				else if(statuslist.isEmpty()) {
					statuslist.add("");
					resultList=userRepo.findBySkillsSkillNameInAndExperienceYears(namelist,experience);
					
					}
				else {
					resultList=userRepo.findBySkillsSkillNameInOrExperienceYearsOrStatusIn(namelist,experience,statuslist);
					
				}		
				
				}
			else if(expdata.equals("Less Than")) {
				if(statuslist.isEmpty() && namelist.isEmpty()) {
					statuslist.add("");
					namelist.add("");
				
					resultList=userRepo.findByExperienceYearsLessThan(experience);
						}
				else if(namelist.isEmpty()) {
					namelist.add("");
					resultList=userRepo.findByExperienceYearsLessThanAndStatusIn(experience,statuslist);
					
					}
				else if(statuslist.isEmpty()) {
					statuslist.add("");
					resultList=userRepo.findBySkillsSkillNameInAndExperienceYearsLessThan(namelist,experience);
					
					}
				else {
					resultList=userRepo.findBySkillsSkillNameInOrExperienceYearsLessThanOrStatusIn(namelist,experience,statuslist);
					
				}
						
			}else {
				if(statuslist.isEmpty() && namelist.isEmpty()) {
					statuslist.add("");
					namelist.add("");
				
					resultList=userRepo.findByExperienceYearsGreaterThan(experience);
							}
				else if(namelist.isEmpty()) {
					namelist.add("");
					resultList=userRepo.findByExperienceYearsGreaterThanAndStatusIn(experience,statuslist);
					
					}
				else if(statuslist.isEmpty()) {
					statuslist.add("");
					resultList=userRepo.findBySkillsSkillNameInAndExperienceYearsGreaterThan(namelist,experience);
						
					}
				else {
					resultList=userRepo.findBySkillsSkillNameInOrExperienceYearsGreaterThanOrStatusIn(namelist,experience,statuslist);
						
				}
				}
			if(!(resultList.isEmpty())) {
	  for(Object[] resultobj1:resultList) {
		  List<String> skillList= new ArrayList<>(); 
		  FilterResult temp= new FilterResult();
		  temp.setEmpId((int) resultobj1[0]);
		  temp.setEmployeeName(resultobj1[1].toString());
		  temp.setExperienceYears((int)resultobj1[2]);
		  temp.setSkillName((String) resultobj1[3]);
		  temp.setStatus((String)resultobj1[4]);
		  skillList.add((String) resultobj1[3]);
		  resultobj.add(temp);
			  status.setCode(200);
		     status.setMessage("Success");
		     object.setStatus(status);
		     filterResult.setFilterlist(resultobj);
		      object.setResult(filterResult);
		
	  }  
	  }	
			else {
				 status.setCode(400);
			     status.setMessage("No Data Found from Database for Search Criteria");
			     object.setStatus(status);
			     filterResult.setFilterlist(null);
			      object.setResult(filterResult);
			   
			   //  object.setFilterResult(null);
			     return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);

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
		Result filterResult = new Result();
		 ResponseObject object=new ResponseObject();
			 FilterResult result= new FilterResult();
				List<Object[]> temp = new ArrayList<>();
		try {
			
			if(userdata.getEmpId()!=0 && !(userdata.getEmployeeEmail().isEmpty())) {
				 temp = userRepo.findByEmpIdAndEmployeeEmail(userdata.getEmpId(), userdata.getEmployeeEmail());
				
			}
			else {
				if(userdata.getEmpId()!=0 ) {
					 temp = userRepo.findByEmpIdOrEmployeeEmail(userdata.getEmpId(), userdata.getEmployeeEmail());
					
				}else if(!(userdata.getEmployeeEmail().isEmpty())) {
					 temp = userRepo.findByEmpIdOrEmployeeEmail(userdata.getEmpId(), userdata.getEmployeeEmail());
					
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
		if(!(temp.isEmpty())) {
			for(Object[] resultobj1:temp) {
					    	
			    	  result.setEmpId((int) resultobj1[0]);
			    	  result.setEmployeeName(resultobj1[1].toString());
			    	  result.setStatus((String)resultobj1[2]);
			    	  result.setExperienceYears((int)resultobj1[3]);
			    	  result.setSkillName((String) resultobj1[4]);
			    	  status.setCode(200);
					     status.setMessage("Success");
					     object.setStatus(status);
					     filterResult.setFilterResult(result);
					      object.setResult(filterResult);
					 
		    	 }  
			} else {
		    		 status.setCode(200);
				     status.setMessage("No Data Found for Search Result from Database");
				     object.setStatus(status);
				     filterResult.setFilterResult(null);
				     return new ResponseEntity<>(object, HttpStatus.OK);
		 
		    	 }  
			}catch(Exception e) {
				 e.printStackTrace();
			}
			  
	   
		return new ResponseEntity<>(object, HttpStatus.OK);
 	    
	}

	@Override
	public ResponseEntity<ResponseObject> searchbyId(int id) {
		Result filterResult = new Result();
		 ResponseObject object=new ResponseObject();
		try {
			if(id!=0) {
	        UserInfo user = userRepo.findByEmpId(id);
	         if(user!=null && user.getEmpId()!=0) {
	        	        		 if(user.isFlag()==true) {
	        	 filterResult.setDesignation(user.getDesignation());
	        	 filterResult.setEmpId(user.getEmpId());
	        	 filterResult.setEmployeeEmail(user.getEmployeeEmail());
	        	 filterResult.setStatus(user.getStatus());
	        	 filterResult.setProjectManager(user.getProjectManager());
	        	 filterResult.setEmployeeName(user.getEmployeeName());
	        	 filterResult.setExperienceYears(user.getExperienceYears());
	        	 filterResult.setExperienceMonths(user.getExperienceMonths());
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
	                object.setResult(filterResult);
	           		}
	           		filterResult.setSkills(skills);
	                    }
	        	        		 else {
	        	                	  status.setCode(400);
	        	          		     status.setMessage("This user has been removed");
	        	          		     object.setStatus(status);
	        	          		     object.setResult(null);
	        	          				return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
	        	             
	        	                 }
	         }
	            		   
	               else {
	            	   status.setCode(400);
	        		     status.setMessage("No data found for EmpID");
	        		     object.setStatus(status);
	        				return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
	            	   
	               }   
			}else {
				  status.setCode(400);
     		     status.setMessage("EmpId is null");
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
		Result filterResult = new Result();
		 ResponseObject object=new ResponseObject();
		 UserInfo saveuser = new UserInfo();
		 try {
			 if(userinfo.getEmpId()!=0) {
		 UserInfo userById = userRepo.findByEmpId(userinfo.getEmpId());
		if(userById.isFlag()==true) {
			 if( userById!=null && userById.getEmpId()!=0 ) {
				 userById.setFlag(false);
				userRepo.save(userById);
				 status.setCode(200);
			     status.setMessage("Success");
			     object.setStatus(status);
				
				}
		 else {
			 status.setCode(400);
		     status.setMessage("Error in deleting , empid may be wrong");
		     object.setStatus(status);
			return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
		 }
		}else {
      	  status.setCode(200);
		     status.setMessage("This user no more exists in database");
		     object.setStatus(status);
		     object.setResult(null);
				return new ResponseEntity<>(object, HttpStatus.OK);
   
       }
			 }
		 else {
			 status.setCode(400);
		     status.setMessage("EmpId is null");
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
