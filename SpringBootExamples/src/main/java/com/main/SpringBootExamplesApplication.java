package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import com.springboot.controllers.GreetingController;

@SpringBootApplication
@ImportResource("classpath:spring/spring-config.xml")
public class SpringBootExamplesApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootExamplesApplication.class, args);
		GreetingController greetingController = (GreetingController)context.getBean("greetingController");
		System.out.println(greetingController.getGreeting());
	}
}
