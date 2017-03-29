package com.springboot.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springboot.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

}
