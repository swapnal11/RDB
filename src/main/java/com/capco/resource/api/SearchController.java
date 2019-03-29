package com.capco.resource.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capco.resource.model.FilterData;
import org.springframework.core.io.ByteArrayResource;
import com.capco.resource.model.FilterResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
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
	
	
	
	@PostMapping(value = "/filterSearch")
    public ResponseEntity<ResponseObject> searchResult(@RequestBody FilterData result ) {
           
		 
				
		return serachservice.searchResult(result);
           
         
           
           }
	
	@PostMapping(value = "/searchUser")
    public  ResponseEntity<ResponseObject> searchbyIdAndEmail(@RequestBody UserInfo user ) {
           
			
		return serachservice.searchbyIdAndEmail(user);
           
         
           
           }
	
	@GetMapping(value = "/searchUser/{id}")
    public  ResponseEntity<ResponseObject> searchbyId(@PathVariable int id ) {
           
			
		return serachservice.searchbyId(id);
           
         
           
           }
	
	@PostMapping(value = "/deleteUserById")
    public  ResponseEntity<ResponseObject> deleteUserbyId(@RequestBody UserInfo user ) {
           
	
		return serachservice.deleteUserbyId(user);
           
         
           
           }
	
	 @GetMapping("/downloadFile/{empId}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable int empId) {

	        UserInfo dbFile = serachservice.getFile(empId);

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
	                .body(new ByteArrayResource(dbFile.getData()));
	    }


}
