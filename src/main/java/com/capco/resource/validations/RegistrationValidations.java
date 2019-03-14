package com.capco.resource.validations;

import java.util.regex.Pattern;

import com.capco.resource.exceptions.CustomerException;
import com.capco.resource.model.UserInfo;

public class RegistrationValidations {
	
	/*public void addHrValidations(UserInfo userinfo) throws CustomerException {
		
				
		if (userinfo.getEmpId() == 0|| userinfo.getEmployeeEmail()== ""|| userinfo.getEmployeeEmail()== null 
									|| userinfo.getEmployeeName()== ""|| userinfo.getEmployeeName()== null 
									|| userinfo.getDesignation()== "" || userinfo.getDesignation()==null 
									|| userinfo.getProjectManager()=="" || userinfo.getProjectManager()==null 
									|| userinfo.getStatus()== ""|| userinfo.getStatus()==null
									|| userinfo.getExperienceMonths()==0||userinfo.getExperienceYears()==0||userinfo.getSkills().isEmpty()) {
			throw new CustomerException("Enter the Mandatory Field");
		}
		
	}*/
	
	
	
public static void lengthValidationForName(String name) throws CustomerException {
		
		if( name.isEmpty() )
			throw new CustomerException(5001);
		
	}

public static void lengthValidationForNumber(int number) throws CustomerException {
	
	if( number==0 )
		throw new CustomerException(5001);
	
}
	
	 public static void isEmailValid(String email) throws CustomerException
	    { 
	      /*  String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
	                            "[a-zA-Z0-9_+&*-]+)*@" + 
	                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
	                            "A-Z]{2,7}$"; */
		  String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                  "[a-zA-Z0-9_+&*-]+)*@" + 
                  "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                  "A-Z]{2,7}$"; 
	                              
	        Pattern pat = Pattern.compile(emailRegex); 
	        if (!(pat.matcher(email).matches()) || email == null) 
	        	throw new CustomerException(5002);
	    } 
	 
	 public static void isValidPassword(String password)throws CustomerException{
		 if(!(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$") || password == null))
			 throw new CustomerException(5003);
		
	 }
	 
	

}
