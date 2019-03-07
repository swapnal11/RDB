package com.capco.resource.model;

import java.util.List;

public class FilterResult {

	
	private int empId;
	
	private String employeeName;
	
	private String experience;
	
	private List<String> skillNameList;
	
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

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public List<String> getSkillNameList() {
		return skillNameList;
	}

	public void setSkillNameList(List<String> skillNameList) {
		this.skillNameList = skillNameList;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

 


	
	
	
}
