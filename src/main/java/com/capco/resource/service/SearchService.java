package com.capco.resource.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capco.resource.model.FilterData;
import com.capco.resource.model.FilterResult;
import com.capco.resource.model.ResponseObject;
import com.capco.resource.model.Skill;
import com.capco.resource.model.UserInfo;

public interface SearchService {
	
	 public ResponseEntity<ResponseObject> searchResult(FilterData result);
	 
	 public  ResponseEntity<ResponseObject> searchbyIdAndEmail(UserInfo user);
	 	 
	 public  ResponseEntity<ResponseObject> searchbyId(int id);
		
	 public  ResponseEntity<ResponseObject> deleteUserbyId(UserInfo user);
	 
	 public UserInfo getFile(int fileId);
		

}
