package com.springboot.service.reposervices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.springboot.domain.Customer;
import com.springboot.repositories.CustomerRepository;
import com.springboot.service.CustomerService;

@Service
@Profile({"springdatajpa"})
public class CustomerServiceRepoJPAImpl implements CustomerService {

	private CustomerRepository customerRepository;
	
	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository=customerRepository;
	}

	@Override
	public List<Customer> listAll() {
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customer -> customers.add(customer));;
		return customers;
	}

	@Override
	public Customer getById(Integer id) {
		return customerRepository.findOne(id);
	}

	@Override
	public Customer saveOrUpdate(Customer domainObject) {
		return customerRepository.save(domainObject);
	}

	@Override
	public void delete(Integer id) {
		customerRepository.delete(id);
	}
	
	

}
