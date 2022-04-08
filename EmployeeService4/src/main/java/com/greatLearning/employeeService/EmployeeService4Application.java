package com.greatLearning.employeeService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.greatLearning.employeeService")
public class EmployeeService4Application {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeService4Application.class, args);
	}

}
