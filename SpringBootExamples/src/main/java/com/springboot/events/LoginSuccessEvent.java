package com.springboot.events;

import org.springframework.context.ApplicationEvent;

public class LoginSuccessEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;

	public LoginSuccessEvent(Object source) {
		super(source);
	}
	
}
