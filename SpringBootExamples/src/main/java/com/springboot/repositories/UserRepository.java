package com.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {//extends CrudRepository<User, Integer> {

	Optional<User> findByUserName(String username);

}
