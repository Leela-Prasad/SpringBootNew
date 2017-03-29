package com.springboot.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springboot.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
