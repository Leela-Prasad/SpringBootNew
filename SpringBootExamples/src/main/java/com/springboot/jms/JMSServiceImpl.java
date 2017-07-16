package com.springboot.jms;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.springboot.service.JMSService;

@Service
public class JMSServiceImpl implements JMSService{

	private JmsTemplate jmsTemplate;
	
	@Autowired
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate=jmsTemplate;
	}
	
	@Override
	public void sendMessage(Destination queue,String msg) {
		this.jmsTemplate.convertAndSend(queue, msg);
	}

}
