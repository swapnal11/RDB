package com.capco.resource.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class FilterData implements Serializable{
	

	
	private List<String> status;
 
 private int experienceYear;
 
 private List<String> skillName;
 
 

public FilterData() {
	super();
}




public int getExperienceYear() {
	return experienceYear;
}




public void setExperienceYear(int experienceYear) {
	this.experienceYear = experienceYear;
}




public List<String> getStatus() {
	return status;
}

public void setStatus(List<String> status) {
	this.status = status;
}


public List<String> getSkillName() {
	return skillName;
}



public void setSkillName(List<String> skillName) {
	this.skillName = skillName;
}


	

}
