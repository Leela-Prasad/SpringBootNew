package com.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetterInjectionService {

	@Autowired
	private HelloWorldService helloWorldService;
	
	public void setHelloWorldService(HelloWorldService helloWorldService) {
		this.helloWorldService=helloWorldService;
	}
	
	public void getMessage() {
		helloWorldService.sayHello();
	}
}
