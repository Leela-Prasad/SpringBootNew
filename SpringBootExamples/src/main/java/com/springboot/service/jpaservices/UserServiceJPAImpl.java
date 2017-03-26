package com.springboot.service.jpaservices;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.springboot.domain.User;
import com.springboot.service.CRUDServiceImpl;
import com.springboot.service.security.EncryptionService;

@Service
@Profile("jpa")
public class UserServiceJPAImpl extends CRUDServiceImpl<User> implements UserService{

	private EncryptionService encryptorService;
	
	@Autowired
	public void setEncryptorService(EncryptionService encryptorService) {
		this.encryptorService=encryptorService;
	}
	
	@Override
	public User saveOrUpdate(User domainObject) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		if(domainObject.getPassword()!=null) {
			System.out.println("Password Updated!!!");
			domainObject.setEncryptedPassword(encryptorService.encryptString(domainObject.getPassword()));
		}
		User savedUser = em.merge(domainObject);
		em.getTransaction().commit();
		return savedUser;
	}

}
