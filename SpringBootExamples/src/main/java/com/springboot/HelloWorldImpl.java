package com.springboot;

import org.springframework.stereotype.Component;

@Component
public class HelloWorldImpl implements HelloWorldService{

	public void sayHello() {
		System.out.println("Hello Spring Boot!!!");
	}
}
