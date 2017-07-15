package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {//extends CrudRepository<User, Integer> {

	User findByUserName(String username);

}
