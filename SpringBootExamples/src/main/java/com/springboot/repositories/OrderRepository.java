package com.springboot.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springboot.domain.Orders;

public interface OrderRepository extends CrudRepository<Orders, Integer> {

}
