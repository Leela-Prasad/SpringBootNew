package com.springboot.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBootApplicationContext implements ApplicationContextAware{

	private static ApplicationContext context;
	
	@Autowired
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		SpringBootApplicationContext.context=context;
	}
	
	public static ApplicationContext getContext() {
		return context;
	}

}
