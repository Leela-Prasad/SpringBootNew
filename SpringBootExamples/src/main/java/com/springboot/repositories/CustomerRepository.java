package com.springboot.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springboot.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{

}
