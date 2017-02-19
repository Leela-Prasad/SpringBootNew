package com.springboot.controllers;

import com.springboot.services.HelloWorldService;

public class GreetingController {

	private HelloWorldService helloWorldService;
	
	public void setHelloWorldService(HelloWorldService helloWorldService) {
		this.helloWorldService=helloWorldService;
	}
	
	public String getGreeting() {
		return helloWorldService.sayHello();
	}
	
}
