package com.springboot.listeners;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.springboot.domain.User;
import com.springboot.events.LoginFailureEvent;
import com.springboot.repositories.UserRepository;
import com.springboot.service.jpaservices.UserService;

@Component
public class LoginFailureListener implements ApplicationListener<LoginFailureEvent>{

	private UserRepository userRepository; 
	private UserService userService;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService=userService;
	}
	
	@Override
	public void onApplicationEvent(LoginFailureEvent event) {
		Authentication authentication = (Authentication)event.getSource();
		System.out.println("From LoginFailure Listener");
		System.out.println("Login failed for user :" +(String)authentication.getPrincipal());
		updateUserAccount(authentication);
	}

	private void updateUserAccount(Authentication authentication) {
		/*User user = userRepository.findByUserName((String)authentication.getPrincipal());
		
		if(user!=null) {
			Integer failAttempts = user.getNoOfFailureAttempts();
			if(failAttempts==null) {
				failAttempts=1;
				user.setNoOfFailureAttempts(1);
			}	
			else {
				++failAttempts;
				user.setNoOfFailureAttempts(failAttempts);
			}
				
			
			if(failAttempts > 3) {
				user.setEnabled(false);
			}
			
			userService.saveOrUpdate(user);
		}*/
		
		Optional<User> optional = userRepository.findByUserName((String)authentication.getPrincipal());
		
		optional.ifPresent(user -> {
			Integer failAttempts = user.getNoOfFailureAttempts();
			if(failAttempts==null) {
				failAttempts=1;
				user.setNoOfFailureAttempts(1);
			}	
			else {
				++failAttempts;
				user.setNoOfFailureAttempts(failAttempts);
			}
				
			if(failAttempts >= 3) {
				user.setEnabled(false);
			}
			
			userService.saveOrUpdate(user);
		});
	}

	
}
