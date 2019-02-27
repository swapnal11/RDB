package com.capco.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.capco.resource.model.UserInfo;

@Repository
public interface UserRepo extends JpaRepository<UserInfo, Long> {
	
	UserInfo findByEmpId(Long empId);
	
	

}
