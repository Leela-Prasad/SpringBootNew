package com.springboot.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("spanish")
public class HelloWorldServiceSpanishImpl implements HelloWorldService{

	@Override
	public void sayHello() {
		System.out.println("Hola Mundo!!!");
		
	}

}
