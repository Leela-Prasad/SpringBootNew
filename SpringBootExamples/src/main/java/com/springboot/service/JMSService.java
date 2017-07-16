package com.springboot.service;

import javax.jms.Destination;

public interface JMSService {
	
	public void sendMessage(Destination queue,String msg);

}
