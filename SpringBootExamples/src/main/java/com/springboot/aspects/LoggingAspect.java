package com.springboot.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.springboot.events.LoginFailureEvent;
import com.springboot.events.LoginSuccessEvent;
import com.springboot.publishers.LoginFailurePublisher;
import com.springboot.publishers.LoginSuccessPublisher;

@Aspect
@Component
public class LoggingAspect {

	private LoginFailurePublisher failurePublisher;
	private LoginSuccessPublisher successPublisher;
	
	@Autowired
	public void setFailurePublisher(LoginFailurePublisher failurePublisher) {
		this.failurePublisher=failurePublisher;
	}
	
	@Autowired
	public void setSuccessPublisher(LoginSuccessPublisher successPublisher) {
		this.successPublisher=successPublisher;
	}
	
	@Pointcut("execution(* org.springframework.security.authentication.AuthenticationProvider.authenticate(..))")
	public void doAuthenticate() {
		
	}
	
	@Before("doAuthenticate() && args(authentication)")
	public void logBeforeAuthentication(Authentication authentication) {
		System.out.println("Before Authentication" + authentication.isAuthenticated());
	}
	
	@AfterReturning(value="doAuthenticate()",returning="authentication")
	public void logAfterAuthentication(Authentication authentication) {
		successPublisher.publish(new LoginSuccessEvent(authentication));
		//System.out.println("After Authentication " + authentication.isAuthenticated());
	}
	
	@AfterThrowing("doAuthenticate() && args(authentication)")
	public void logAuthenticationException(Authentication authentication) {
		failurePublisher.publish(new LoginFailureEvent(authentication));
		//System.out.println("Login failed for user :" +(String)authentication.getPrincipal());
	}
}
