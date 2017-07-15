package com.springboot.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.springboot.events.LoginFailureEvent;

@Component
public class LoginFailurePublisher implements ApplicationEventPublisherAware{

	@Autowired
	ApplicationEventPublisher publisher;
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher=publisher;
		
	}
	
	public void publish(LoginFailureEvent event) {
		publisher.publishEvent(event);
	}

}
