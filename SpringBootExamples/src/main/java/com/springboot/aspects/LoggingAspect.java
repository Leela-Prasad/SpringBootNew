package com.springboot.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@Pointcut("execution(* org.springframework.security.authentication.AuthenticationProvider.authenticate(..))")
	public void doAuthenticate() {
		
	}
	
	@Before("doAuthenticate() && args(authentication)")
	public void logBeforeAuthentication(Authentication authentication) {
		System.out.println("Before Authentication" + authentication.isAuthenticated());
	}
	
	@AfterReturning(value="doAuthenticate()",returning="authentication")
	public void logAfterAuthentication(Authentication authentication) {
		System.out.println("After Authentication " + authentication.isAuthenticated());
	}
	
	@AfterThrowing("doAuthenticate() && args(authentication)")
	public void logAuthenticationException(Authentication authentication) {
		System.out.println("Login failed for user :" +(String)authentication.getPrincipal());
	}
}
