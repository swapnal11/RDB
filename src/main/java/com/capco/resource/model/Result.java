package com.capco.resource.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)

public class Result implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long empId;
	private String employeeName;
	private String employeeEmail;
	private String designation;
	private String password;
	
	@OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = false,
            mappedBy = "Result",
            fetch = FetchType.EAGER)
    
	private List<Skill> skills;
	
	
	private String status;
	
	
	private String projectManager;
	
	
	private String experience;


	public Long getEmpId() {
		return empId;
	}


	public List<Skill> getSkills() {
		return skills;
	}


	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}


	public void setEmpId(Long empId) {
		this.empId = empId;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getEmployeeEmail() {
		return employeeEmail;
	}


	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getProjectManager() {
		return projectManager;
	}


	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}


	public String getExperience() {
		return experience;
	}


	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	

	
}






