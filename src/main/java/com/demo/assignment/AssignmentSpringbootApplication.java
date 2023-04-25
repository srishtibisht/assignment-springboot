package com.demo.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AssignmentSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentSpringbootApplication.class, args);
	}

}
