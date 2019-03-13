package com.capco.resource.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.capco.resource.exceptions.CustomerException;
import com.capco.resource.model.ResponseObject;
import com.capco.resource.model.Status;




@RestControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(CustomerException.class)
	public final ResponseEntity<ResponseObject> handlePaymentExceptions(CustomerException e, WebRequest request)
			throws IOException {


		Status status = new Status();
		 ResponseObject object=new ResponseObject();
		status.setCode(e.getCode());
		status.setMessage(getErrorMessage(e.getCode()+""));
		object.setStatus(status);
		//status.setDetails(request.getDescription(false));
		e.printStackTrace();


		return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
	}
	
	public String getErrorMessage(String errorCode) throws IOException {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("error_messages.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		return properties.getProperty(errorCode);
	}

}
