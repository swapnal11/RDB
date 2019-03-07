  package com.capco.resource.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="employeeInfo")
@JsonInclude(Include.NON_DEFAULT)

public class UserInfo {
	
	
	@Id	
	@Column(name="emp_id")
	private int empId;
	
	@Column
	private String employeeName;
	
	@Column
	private String employeeEmail;
	
	@Column
	private String designation;
	
	@Column
	private String password;
	
	@Column
	private String status;
	
	@Column
	private String projectManager;
	
	@Column
	private String experience;
	
	//@OneToMany(cascade = CascadeType.PERSIST)
	@OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = false,
            mappedBy = "userInfo",
            fetch = FetchType.LAZY
    )
	private List<Skill> skills;
	
	/* @OneToMany(
	            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
	            mappedBy = "employeeInfo",
	            fetch = FetchType.EAGER
	    )
	   
	    private List<Skill> skills;
*/
	

	public String getExperience() {
		return experience;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public void setExperience(String experience) {
		this.experience = experience;
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

	public UserInfo() {
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

	@Override
	public String toString() {
		return "UserInfo [empId=" + empId + ", employeeName=" + employeeName + ", employeeEmail=" + employeeEmail
				+ ", designation=" + designation + ", password=" + password + ", status=" + status + ", projectManager="
				+ projectManager + ", experience=" + experience + ", skills=" + skills + "]";
	}

	
	
	
	


}
