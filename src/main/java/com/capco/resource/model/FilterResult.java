package com.capco.resource.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class FilterResult {

	
	private int empId;
	
	private String employeeName;
	
	private int experienceYear;
	
	private String skillName;
	
	

	public FilterResult() {
		super();
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	

	public int getExperienceYear() {
		return experienceYear;
	}

	public void setExperienceYear(int experienceYear) {
		this.experienceYear = experienceYear;
	}


	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

 


	
	
	
}
