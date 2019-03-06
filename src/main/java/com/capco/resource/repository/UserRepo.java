package com.capco.resource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capco.resource.model.FilterResult;
import com.capco.resource.model.UserInfo;

@Repository
public interface UserRepo extends JpaRepository<UserInfo, Long> {
	
	UserInfo findByEmpId(int i);
	
	//List<UserInfo> findByExperience(String experience);
	
	// List<UserInfo> findByExperienceAndStatusIn(String experience,List<String> status);  
	
	/*
	@Query(value="select * from employee_info e, skills s where e.emp_id=s.emp_id and e.status in(:status) and e.experience > :experience and s.skill in(:skill)",nativeQuery= true)
	List<UserInfo> findByUserInfoExperienceAndUserInfoStatuINAndSkillSkillNameIN(@Param("experience") String experience, @Param("status") List<String> status, @Param("skill") List<String> skill);
	*/
	@Query(value="select e.emp_id,e.employee_name,e.experience from employee_info e where e.experience > :experience and e.status in(:status) ",nativeQuery= true)
	List<FilterResult> findByExperienceAndStatusIn(@Param("experience") String experience, @Param("status") List<String> status);
	
	/*
	@Query(value="select * from ResourceDb.employee_info e join ResourceDb.skills s on e.emp_id=s.emp_id where e.emp_id=:emp_id",nativeQuery= true)
	List<UserInfo> findByAll(@Param("emp_id") int empId);
	*/

}
