package com.capco.resource.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capco.resource.model.FilterData;
import com.capco.resource.model.FilterResult;
import com.capco.resource.model.ResponseObject;
import com.capco.resource.model.Skill;
import com.capco.resource.model.UserInfo;
import com.capco.resource.service.RegistrationService;
import com.capco.resource.service.SearchService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/api")
public class SearchController {
	
	
ResponseEntity response;
	
	@Autowired
	SearchService serachservice;
	
	@PostMapping(value = "/search")
    public List<FilterResult> searchResult(@RequestBody FilterData result ) {
           
		return serachservice.searchResult(result);
           
         
           
           }

	

}
