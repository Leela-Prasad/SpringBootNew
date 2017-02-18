package com.springboot.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Profile("default")			uses this default profile if nothing is mentioned in application.properties file
@Profile({"default","english"}) //can be a default profile or profile with english name
public class HelloWorldServiceEnglishImpl implements HelloWorldService {

	@Override
	public void sayHello() {
		System.out.println("Hello World!!!");
		
	}

}
