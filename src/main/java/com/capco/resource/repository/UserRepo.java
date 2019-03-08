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
	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience experience ,s.skill from employee_info e, skills s where e.emp_id=s.emp_id and e.experience = :experience and e.status in(:status)  and s.skill in(:skill)",nativeQuery= true)
	List<Object[]> findBySkillsSkillNameInAndExperienceAndStatusIn(@Param("skill") List<String> skill, @Param("experience") String experience,@Param("status") List<String> status);
	*/
/*	@Query(value="select e.emp_id,e.employee_name,e.experience from employee_info e where e.experience > :experience and e.status in(:status) ",nativeQuery= true)
	List<FilterResult> findByExperienceAndStatusIn(@Param("experience") String experience, @Param("status") List<String> status);
	*/
	
	/*@Query(value="select  e.emp_id empId ,e.employee_name employeeName,e.experience experience from employee_info e where e.experience > :experience and e.status =:status ",nativeQuery= true)
	List<UserInfo> findBySkillsSkillNameInAndExperienceAndStatusIn(List<String> skill, String experience,List<String> status);
	*/
	
	
	/*
	@Query(value="select * from ResourceDb.employee_info e join ResourceDb.skills s on e.emp_id=s.emp_id where e.emp_id=:emp_id",nativeQuery= true)
	List<UserInfo> findByAll(@Param("emp_id") int empId);
	*/
	
	
	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience_year experienceYear ,GROUP_CONCAT(distinct s.skill) from employee_info e, skills s where e.emp_id=s.emp_id and e.experience_year = :experience_year and e.status in(:status)  and s.skill in(:skill) group by s.emp_Id",nativeQuery= true)
	List<Object[]> findBySkillsSkillNameInAndExperienceYearAndStatusIn(@Param("skill") List<String> skill, @Param("experience_year") int experience_year,@Param("status") List<String> status);
	

}
