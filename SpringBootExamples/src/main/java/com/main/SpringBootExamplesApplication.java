package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.springboot.controllers.GreetingController;

@SpringBootApplication
/*
 * If this component scan annotation is not present then spring bydefault
 * will scan the package and subpackages where the class annotated with springbootapplicaiton.
 * So if other components are present in some other packages then we should tell 
 * spring to scan that package by using componentscan annotation
 */
@ComponentScan("com.springboot")
public class SpringBootExamplesApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootExamplesApplication.class, args);
		GreetingController greetingController = (GreetingController)context.getBean("greetingController");
		greetingController.getGreeting();
	}
}
