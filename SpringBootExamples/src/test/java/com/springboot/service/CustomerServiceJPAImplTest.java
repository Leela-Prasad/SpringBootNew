package com.springboot.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springboot.config.JPAIntegrationConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JPAIntegrationConfiguration.class})
@ActiveProfiles("jpa")
public class CustomerServiceJPAImplTest {

	private CustomerService customerService;
	
	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Test
	public void testListAll() {
		assertThat(customerService.listAll().size(),is(3));
	}

}
