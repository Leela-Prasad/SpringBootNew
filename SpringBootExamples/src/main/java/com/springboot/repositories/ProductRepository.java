package com.springboot.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springboot.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

}
