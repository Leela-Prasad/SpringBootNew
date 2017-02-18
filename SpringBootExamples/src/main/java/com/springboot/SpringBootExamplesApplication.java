package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootExamplesApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootExamplesApplication.class, args);
		ConstructorInjectionService constructorSevice = (ConstructorInjectionService)context.getBean("constructorInjectionService");
		constructorSevice.getMessage();
		
		SetterInjectionService setterService = (SetterInjectionService)context.getBean("setterInjectionService");
		setterService.getMessage();
		
	}
}
