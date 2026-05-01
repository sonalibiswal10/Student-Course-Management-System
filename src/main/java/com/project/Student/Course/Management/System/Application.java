package com.project.Student.Course.Management.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.project.Student.Course.Management.System")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
