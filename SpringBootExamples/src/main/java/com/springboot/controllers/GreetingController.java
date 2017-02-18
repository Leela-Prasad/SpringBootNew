package com.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.springboot.services.HelloWorldService;

@Controller
public class GreetingController {

	private HelloWorldService helloWorldService;
	
	@Autowired
	@Qualifier("french")
	//This will do autowire "byName"
	public void setHelloWorldService(HelloWorldService helloWorldService) {
		this.helloWorldService=helloWorldService;
	}
	
	public void getGreeting() {
		helloWorldService.sayHello();
	}
	
}
