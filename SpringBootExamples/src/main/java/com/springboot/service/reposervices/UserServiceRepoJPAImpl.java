package com.springboot.service.reposervices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.springboot.domain.User;
import com.springboot.repositories.UserRepository;
import com.springboot.service.jpaservices.UserService;
import com.springboot.service.security.EncryptionService;

@Service
@Profile({"springdatajpa"})
public class UserServiceRepoJPAImpl implements UserService {

	private UserRepository userRepository;
	
	private EncryptionService encryptorService;
	
	@Autowired
	public void setEncryptorService(EncryptionService encryptorService) {
		this.encryptorService=encryptorService;
	}
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository=userRepository;
	}

	@Override
	public List<User> listAll() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	public User getById(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public User saveOrUpdate(User domainObject) {
		if(domainObject.getPassword()!=null) {
			domainObject.setEncryptedPassword(encryptorService.encryptString(domainObject.getPassword()));
		}
		return userRepository.save(domainObject);
	}

	@Override
	public void delete(Integer id) {
		userRepository.delete(id);
	}
	
}
