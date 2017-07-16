package com.springboot.config;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JMSConfig {

	public static final String textMsgQueue = "java/jms/textMessageQueue";
	
	@Bean
	public Queue textMsgQueue() {
		return new ActiveMQQueue(textMsgQueue);
	}
	
}
