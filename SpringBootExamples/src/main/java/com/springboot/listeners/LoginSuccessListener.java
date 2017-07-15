package com.springboot.listeners;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.springboot.domain.User;
import com.springboot.events.LoginSuccessEvent;
import com.springboot.repositories.UserRepository;
import com.springboot.service.jpaservices.UserService;

@Component
public class LoginSuccessListener implements ApplicationListener<LoginSuccessEvent>{

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
	public void onApplicationEvent(LoginSuccessEvent event) {
		Authentication authentication = (Authentication)event.getSource();
		System.out.println("Event from Login Success Listener");
		System.out.println("successfully logged in for user :" + ((UserDetails)authentication.getPrincipal()).getUsername());
		updateUserAccount(authentication);
	}

	private void updateUserAccount(Authentication authentication) {
		String userName = ((UserDetails)authentication.getPrincipal()).getUsername();
		Optional<User> optional = userRepository.findByUserName(userName);
		optional.ifPresent(user -> {
			user.setNoOfFailureAttempts(0);
			userService.saveOrUpdate(user);
		});
	}

	
}
