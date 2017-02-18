package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.springboot.controllers.GreetingController;

@SpringBootApplication
public class SpringBootExamplesApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootExamplesApplication.class, args);
		GreetingController greetingController = (GreetingController)context.getBean("greetingController");
		greetingController.getGreeting();
	}
}
