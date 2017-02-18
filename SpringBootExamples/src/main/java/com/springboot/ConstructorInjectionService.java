package com.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConstructorInjectionService {

	@Autowired
	private HelloWorldService helloWorldService;
	
	public ConstructorInjectionService() {
		super();
	}

	public ConstructorInjectionService(HelloWorldService helloWorldService) {
		super();
		this.helloWorldService = helloWorldService;
	}
	
	public void getMessage() {
		helloWorldService.sayHello();
	}
	
}
