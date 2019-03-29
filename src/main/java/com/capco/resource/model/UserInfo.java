  package com.capco.resource.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
	private int experienceYears;
	
	@Column
	private int experienceMonths;
	
	@Column
	private String sqQuestion;
	
	@Column
	private String sqAnswer;
	
	@Column(columnDefinition = "BIT(1) default 1")
    private Boolean flag = true;
	
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
    
    
    @Lob
    private byte[] imagedata;
  
	
	

	
	public byte[] getImagedata() {
		return imagedata;
	}

	public void setImagedata(byte[] imagedata) {
		this.imagedata = imagedata;
	}

	//@OneToMany(cascade = CascadeType.PERSIST)
	@OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = false,
            mappedBy = "userInfo",
            fetch = FetchType.LAZY
    )
	private List<Skill> skills;
/*	
	@OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = false,
            mappedBy = "userInfo",
            fetch = FetchType.LAZY
    )
	private List<DBFile> dbfile;*/
	
	
	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
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

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
		
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

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
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

	@Override
	public String toString() {
		return "UserInfo [empId=" + empId + ", employeeName=" + employeeName + ", employeeEmail=" + employeeEmail
				+ ", designation=" + designation + ", password=" + password + ", status=" + status + ", projectManager="
				+ projectManager + ", experienceYear=" + experienceYears + ", experienceMonth=" + experienceMonths
				+ ", skills=" + skills + "]";
	}

	
	

	

	
	
	
	


}
