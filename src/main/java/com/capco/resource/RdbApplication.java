package com.capco.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.capco.resource")
public class RdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(RdbApplication.class, args);
	}

}
