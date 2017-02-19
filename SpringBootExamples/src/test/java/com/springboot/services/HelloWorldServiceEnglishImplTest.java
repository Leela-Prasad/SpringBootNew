package com.springboot.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-config.xml", 
								"classpath:spring/english-helloworld.xml"})
public class HelloWorldServiceEnglishImplTest {

	@Autowired
	private HelloWorldService helloWorldService;
	
	@Test
	public void testEnglishImpl() {
		System.out.println(helloWorldService.sayHello());
		assertEquals("Hello World!!!", helloWorldService.sayHello());
	}

}
