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
import com.capco.resource.model.Skill;
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
	
	@Autowired(required=true)
	EntityManager entityManager;
	
	ModelMapper modelMapper = new ModelMapper();

	private Object namelist;

	@Override
	public List<FilterResult> searchResult(FilterData result) {
		List<FilterResult> resultobj = new ArrayList<>();
	
		List<FilterResult> resultdata = new ArrayList<>();
		try {
			String experience = result.getExperience();
			 List<String> statuslist = result.getStatus();
			 List<String> namelist = result.getSkillName();
			 
			
			List<Object[]>	resultList=userRepo.findBySkillsSkillNameInAndExperienceAndStatusIn(namelist,experience,statuslist);
			System.out.println("q----"+resultList);
	  for(Object[] resultobj1:resultList) {
		  
		  List<String> skillList= new ArrayList<>(); 
		  FilterResult temp= new FilterResult();
		  temp.setEmpId((int) resultobj1[0]);
		  temp.setEmployeeName(resultobj1[1].toString());
		  temp.setExperience((String) resultobj1[2]);
		  temp.setSkillName((String) resultobj1[3]);

		  skillList.add((String) resultobj1[3]);
		  resultobj.add(temp);
	  }  
	  
	 /* Map<Integer, List<FilterResult>> personByCity = new HashMap<>(); 
	  for(FilterResult p : resultobj)
	  { if(!personByCity.containsKey(p.getEmpId()))
	  { personByCity.put(p.getEmpId(), new ArrayList<>()); }
	  personByCity.get(p.getEmpId()).add((FilterResult) p); }

	  personByCity = resultobj.stream() .collect(Collectors.groupingBy(FilterResult :: getEmpId)); 
	  System.out.println("Person grouped by cities in Java 8: " + personByCity); 

*/
	
	  }
		catch(Exception e) {
			e.printStackTrace();
		}
		return  resultobj;
	

	}

}
