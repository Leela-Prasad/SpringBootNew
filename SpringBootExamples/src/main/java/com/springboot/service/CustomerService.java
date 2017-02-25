package com.springboot.service;

import java.util.List;

import com.springboot.domain.Customer;

public interface CustomerService {

	public List<Customer> listCustomers();
	public Customer getCustomerById(Integer id);
	public Customer saveOrUpdate(Customer customer);
	public void delete(Integer id);
	
}
