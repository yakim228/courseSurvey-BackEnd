package com.ipnetinstitute.csc394.backend;

//package com.ipnetinstitute.csc394.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BackendSurveyApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(BackendSurveyApplication.class, args);
	}

}
