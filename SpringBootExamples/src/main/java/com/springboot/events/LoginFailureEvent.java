package com.springboot.events;

import org.springframework.context.ApplicationEvent;

public class LoginFailureEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;

	public LoginFailureEvent(Object source) {
		super(source);
	}
	
}
