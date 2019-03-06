package com.capco.resource.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class FilterData implements Serializable{
	

	
	private List<String> status;
 
 private String experience;
 
 private List<String> skillName;
 
 

public FilterData() {
	super();
}









public List<String> getStatus() {
	return status;
}

public void setStatus(List<String> status) {
	this.status = status;
}




















public String getExperience() {
	return experience;
}









public void setExperience(String experience) {
	this.experience = experience;
}









public List<String> getSkillName() {
	return skillName;
}



public void setSkillName(List<String> skillName) {
	this.skillName = skillName;
}


	

}
