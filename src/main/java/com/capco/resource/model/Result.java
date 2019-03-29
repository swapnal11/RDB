package com.capco.resource.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_DEFAULT)

public class Result implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int empId;
	private String employeeName;
	private String employeeEmail;
	private String designation;
	private String password;
	@Column
    private String fileName;

	@Column
    private String fileType;
	
	@Column
    private String imageName;

	@Column
    private String imageType;

    @Lob
    private byte[] data;
	
	
	private String status;
	
	
	private String projectManager;
	
	  @Lob
	    private byte[] imagedata;
	  
	
	private int experienceYears;
	
	
	private int experienceMonths;
	
	
	private String sqQuestion;
	
	
	private String sqAnswer;
	
	
	
	 public byte[] getImagedata() {
		return imagedata;
	}


	public void setImagedata(byte[] imagedata) {
		this.imagedata = imagedata;
	}


	private FilterResult filterResult;
	    
	    private List<FilterResult> filterlist = null;
	   

	
	public FilterResult getFilterResult() {
			return filterResult;
		}


		public void setFilterResult(FilterResult filterResult) {
			this.filterResult = filterResult;
		}


		public List<FilterResult> getFilterlist() {
			return filterlist;
		}


		public void setFilterlist(List<FilterResult> filterlist) {
			this.filterlist = filterlist;
		}


	@OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = false,
            mappedBy = "Result",
            fetch = FetchType.EAGER)
    
	private List<Skill> skills;
	

	public int getEmpId() {
		return empId;
	}


	public List<Skill> getSkills() {
		return skills;
	}


	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}


	public void setEmpId(int i) {
		this.empId = i;
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


	public int getExperienceYears() {
		return experienceYears;
	}


	public void setExperienceYears(int experienceYears) {
		this.experienceYears = experienceYears;
	}


	public int getExperienceMonths() {
		return experienceMonths;
	}


	public void setExperienceMonths(int experienceMonths) {
		this.experienceMonths = experienceMonths;
	}


	public String getSqQuestion() {
		return sqQuestion;
	}


	public void setSqQuestion(String sqQuestion) {
		this.sqQuestion = sqQuestion;
	}


	public String getSqAnswer() {
		return sqAnswer;
	}


	public void setSqAnswer(String sqAnswer) {
		this.sqAnswer = sqAnswer;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public String getImageName() {
		return imageName;
	}


	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


	public String getImageType() {
		return imageType;
	}


	public void setImageType(String imageType) {
		this.imageType = imageType;
	}


	public byte[] getData() {
		return data;
	}


	public void setData(byte[] data) {
		this.data = data;
	}


	


	
	
	

	
}






