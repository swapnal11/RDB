package com.capco.resource.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capco.resource.model.FilterData;
import com.capco.resource.model.FilterResult;
import com.capco.resource.model.ResponseObject;
import com.capco.resource.model.Skill;
import com.capco.resource.model.UserInfo;

public interface SearchService {
	
	 public List<FilterResult> searchResult(FilterData result);
	 
	 UserInfo searchbyId(int empId);

}
