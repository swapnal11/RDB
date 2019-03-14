package com.capco.resource.repository;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capco.resource.model.Skill;
import com.capco.resource.model.UserInfo;


@Repository
public interface SkillRepo extends JpaRepository<Skill, Integer> {
	
	List<Skill> findBySkillName(List<String> skillName);
	
	@Transactional
    @Modifying
    @Query(value="delete from skills where emp_id= :emp_id",nativeQuery= true)
	public void deleteByUserInfoEmpId(@Param("emp_id") int emp_id);

    
 

}
