package com.springboot.jms.listeners;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class textMessageQueueListener {

	@JmsListener(destination="java/jms/textMessageQueue")
	public void onMessage(String msg) {
		System.out.println("#######################");
		System.out.println("Received Message : "+msg);
		System.out.println("#######################");
	}
}
