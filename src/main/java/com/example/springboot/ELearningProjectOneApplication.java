
package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(scanBasePackages = "com.example.authentication")
//@SpringBootApplication(scanBasePackages = "com.example.controller")
//(scanBasePackages = {"com.example.authentication", "com.example.controller","com.example.entity","com.example.exceptions","com.example.middleware","com.example.repository","com.example.samples","com.example.service"})
@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.example.repository")
@ComponentScan(basePackages = {"com.example"})
public class ELearningProjectOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ELearningProjectOneApplication.class, args);
		System.out.println("Hello Server");
	}
}


