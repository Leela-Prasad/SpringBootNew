package com.springboot.service.jpaservices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.springboot.domain.Customer;
import com.springboot.service.CRUDServiceImpl;
import com.springboot.service.CustomerService;

@Service
@Profile("jpa")
public class CustomerServiceJPAImpl extends CRUDServiceImpl<Customer> implements CustomerService{
	
}
