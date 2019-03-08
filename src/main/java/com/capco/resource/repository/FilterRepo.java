package com.capco.resource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capco.resource.model.FilterData;
import com.capco.resource.model.UserInfo;


@Repository
public interface FilterRepo extends JpaRepository<UserInfo, String>{
	
	//List<UserInfo> findByExperienceAndStatusIn(String experience,List<String> status); 
	

}
