package com.springboot.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.springboot.domain.User;
import com.springboot.service.LoginService;
import com.springboot.service.jpaservices.UserService;

@Service
public class LoginServiceImpl implements LoginService {

	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService=userService;
	}
	
	//@Scheduled(fixedRate=60000)
	@Override
	public void resetUserLockedAccounts() {
		System.out.println("Resetting User Accounts!");
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) userService.listAll();
		
		users.forEach(user -> {
			if(!user.getEnabled()) {
				System.out.println("Resetting "+ user.getUserName() + " Account!");
				user.setEnabled(true);
				user.setNoOfFailureAttempts(0);
				userService.saveOrUpdate(user);
			}
		});
		
	}

}
