package com.springboot.services;

public class HelloWorldFactory {
	
	public static HelloWorldService createHelloWorldService(String languageCode) {
		HelloWorldService helloWorldService;
		switch(languageCode) {
		case "en": 
					helloWorldService=new HelloWorldServiceEnglishImpl();
					break;
		case "fr": 
					helloWorldService=new HelloWorldServiceFrenchImpl();
					break;
		case "de": 
					helloWorldService=new HelloWorldServiceGermanImpl();
					break;			
		case "pl": 
					helloWorldService=new HelloWorldServicePolishImpl();
					break;
		case "ru": 
					helloWorldService=new HelloWorldServiceRussianImpl();
					break;		
		case "es": 
					helloWorldService=new HelloWorldServiceSpanishImpl();
					break;
		default: 
					helloWorldService=new HelloWorldServiceEnglishImpl();
		}
		return helloWorldService;
	}
}
