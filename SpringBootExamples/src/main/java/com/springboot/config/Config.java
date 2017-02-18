package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.springboot.services.HelloWorldService;
import com.springboot.services.HelloWorldServiceEnglishImpl;
import com.springboot.services.HelloWorldServiceSpanishImpl;

@Configuration	//This annotation tells spring that this class is a configuration class
public class Config {

	@Bean	//This annotation will register this bean with spring context
	//Here method name will be taken as the bean name
	@Profile("english")
	public HelloWorldService helloWorldServiceEnglishImpl() {
		return new HelloWorldServiceEnglishImpl();
	}
	
	@Bean
	@Profile("spanish")
	public HelloWorldService helloWorldServiceSpanishImpl() {
		return new HelloWorldServiceSpanishImpl();
	}
	
}
