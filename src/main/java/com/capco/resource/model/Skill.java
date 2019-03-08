package com.capco.resource.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="skills")
@JsonInclude(Include.NON_NULL)
public class Skill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@ManyToOne(fetch = FetchType.LAZY,  cascade = {CascadeType.PERSIST})
	    @JoinColumn(name="emp_id", nullable=false)
	    private UserInfo userInfo;	
	 
	@Column(name="skill")
	private String skillName;
	
	@Column
	private int skillExperience;
	

	public Skill() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public int getSkillExperience() {
		return skillExperience;
	}

	public void setSkillExperience(int skillExperience) {
		this.skillExperience = skillExperience;
	}

	

	
	
	
	
}
