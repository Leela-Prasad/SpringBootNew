package com.springboot.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.springboot.config.JPAIntegrationConfiguration;
import com.springboot.domain.Customer;
import com.springboot.domain.User;
import com.springboot.service.jpaservices.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JPAIntegrationConfiguration.class})
@ActiveProfiles({"jpa"})
public class UserServiceJPAImplTest {

	UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService=userService;
	}
	
	@Test
	public void testSaveOrUpdate() {
		
		User user = new User();
		user.setUserName("Leela");
		user.setPassword("password");
		
		Customer customer = new Customer();
		customer.setFirstName("leela");
		customer.setLastName("jagu");

		user.setCustomer(customer);
		
		User savedUser = userService.saveOrUpdate(user);
		
		assertNotNull(savedUser.getId());
		assertNotNull(savedUser.getEncryptedPassword());
		assertNotNull(savedUser.getVersion());
		System.out.println("Password :" + savedUser.getEncryptedPassword());
		assertNotNull(savedUser.getCustomer());
		assertNotNull(savedUser.getCustomer().getId());
		
		
	}

}
