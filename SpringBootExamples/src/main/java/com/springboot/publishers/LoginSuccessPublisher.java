package com.springboot.publishers;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.springboot.events.LoginSuccessEvent;

@Component
public class LoginSuccessPublisher implements ApplicationEventPublisherAware{

	private ApplicationEventPublisher publisher;
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher=publisher;
	}
	
	public void publish(LoginSuccessEvent event) {
		publisher.publishEvent(event);
	}

	
}
