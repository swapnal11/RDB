package com.capco.resource.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

	@Override
	public List<FilterResult> searchResult(FilterData result) {
		List<FilterResult> resultobj = new ArrayList<>();

		//	String query="select s  from UserInfo e JOIN e.skills s where e.status in('In Progress','Bench') and s.skillName in('JAVA','C++') and e.experience > 5 ";
		
		List<FilterResult> resultdata = new ArrayList<>();
		try {
			String experience = result.getExperience();
		
			 List<String> statuslist = result.getStatus();
			 List<String> namelist = result.getSkillName();
			System.out.println("Result---"+result.getExperience()+"Skill---"+namelist+"Status-------"+statuslist);
			Query q =entityManager.createNativeQuery("select  e.emp_id empId ,e.employee_name employeeName,e.experience experience,s.skill skill from employee_info e, skills s where e.emp_id=s.emp_id and e.experience > :experience and e.status IN :statuslist  and s.skill IN :namelist").
	setParameter("experience", experience)
			.setParameter("statuslist",statuslist)
	.setParameter("namelist", namelist);
			
			List<Object[]> resultList =  q.getResultList();
	  
	
	  for(Object[] resultobj1:resultList) {
		  FilterResult temp= new FilterResult();
		  temp.setEmpId((int) resultobj1[0]);
		  temp.setEmployeeName(resultobj1[1].toString());
		  temp.setExperience((String) resultobj1[2]);
		  temp.setSkillName( resultobj1[3].toString());
		  resultobj.add(temp);
		  
		 // System.out.println(resultobj[0] +"----------------"+resultobj[1] +"----------------"+resultobj[2] +"----------------"+resultobj[3] +"----------------");
	  
	 // FilterResult filterResult = modelMapper.map(resultList, FilterResult.class);
		 
		/*for(int i=0; i<resultList.size();i++) {
			Object a = resultList.get(0);
		}
		*/	//System.out.println("resultdata---------------"+		resultList.toString());
			/*List<UserInfo> u=  q.getResultList();
			
			System.out.println("resultdata---ppp------------"+			u);
		for(UserInfo obj : u) {
			List<Skill> s = obj.getSkills();
			for(Skill sObj: s) {
			System.out.println("Skillls    :"+sObj.getSkillName());
			}
		}*/
/*resultdata=	userRepo.findByUserInfoExperienceAndUserInfoStatuINAndSkillSkillNameIN(result.getExperience(), result.getStatus(), result.getSkillName());
		
			System.out.println("resultdata------s---------"+resultdata );
			
	*/	
		
		/*resultdata=	userRepo.findByExperienceAndStatusIn(result.getExperience(), result.getStatus());
		
	
	
		System.out.println("resultdata------s---------"+resultdata );
	*/
	
		  System.out.println("resultobj------s---------"+resultList );
	}
	  }
		catch(Exception e) {
			e.printStackTrace();
		}
		return  resultobj;
	

	}

}
