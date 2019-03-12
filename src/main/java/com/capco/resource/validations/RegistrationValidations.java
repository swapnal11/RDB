package com.capco.resource.validations;

import com.capco.resource.exceptions.CustomerException;
import com.capco.resource.model.UserInfo;

public class RegistrationValidations {
	
	public void addHrValidations(UserInfo userinfo) throws CustomerException {
		
				
		if (userinfo.getEmpId() == 0|| userinfo.getEmployeeEmail()== ""|| userinfo.getEmployeeEmail()== null 
									|| userinfo.getEmployeeName()== ""|| userinfo.getEmployeeName()== null 
									|| userinfo.getDesignation()== "" || userinfo.getDesignation()==null 
									|| userinfo.getProjectManager()=="" || userinfo.getProjectManager()==null 
									|| userinfo.getStatus()== ""|| userinfo.getStatus()==null
									|| userinfo.getExperienceMonths()==0||userinfo.getExperienceYears()==0||userinfo.getSkills().isEmpty()) {
			throw new CustomerException("Enter the Mandatory Field");
		}
		
	}

}
