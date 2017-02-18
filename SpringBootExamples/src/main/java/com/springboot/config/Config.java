package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.springboot.services.HelloWorldFactory;
import com.springboot.services.HelloWorldService;

@Configuration	//This annotation tells spring that this class is a configuration class
public class Config {

	@Bean
	@Profile("english")
	@Primary	
	//This annotation indicates if there are two beans with same type then it will take this bean as the primary one  the other one will be ignored
	public HelloWorldService helloWorldServiceEnglishImpl() {
		return HelloWorldFactory.createHelloWorldService("en");
	}
	
	@Bean
	@Profile("spanish")
	@Primary
	public HelloWorldService helloWorldServiceSpanishImpl() {
		return HelloWorldFactory.createHelloWorldService("es");
	}
	
	@Bean("french")
	//This will register this bean with french name 
	//bydefault method name is the bean name
	public HelloWorldService helloWorldServiceFrenchImpl() {
		return HelloWorldFactory.createHelloWorldService("fr");
	}
	
	@Bean("german")
	public HelloWorldService helloWorldServiceGermanImpl() {
		return HelloWorldFactory.createHelloWorldService("de");
	}
	
	@Bean("polish")
	public HelloWorldService helloWorldServicePolishImpl() {
		return HelloWorldFactory.createHelloWorldService("pl");
	}
	
	@Bean("russia")
	public HelloWorldService helloWorldServiceRussianImpl() {
		return HelloWorldFactory.createHelloWorldService("ru");
	}
	
}
