package com.springboot.service.jpaservices;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.springboot.domain.User;
import com.springboot.service.security.EncryptionService;

@Service
@Profile("jpa")
public class UserServiceJPAImpl implements UserService{

	private EntityManagerFactory emf;
	private EncryptionService encryptorService;
	
	@PersistenceUnit
	public void setEmf(EntityManagerFactory emf) {
		this.emf=emf;
	}
	
	@Autowired
	public void setEncryptorService(EncryptionService encryptorService) {
		this.encryptorService=encryptorService;
	}
	
	@Override
	public List<User> listAll() {
		EntityManager em = emf.createEntityManager();
		return em.createQuery("from User").getResultList();
	}

	@Override
	public User getById(Integer id) {
		EntityManager em = emf.createEntityManager();
		return em.find(User.class, id);
	}

	@Override
	public User saveOrUpdate(User domainObject) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		if(domainObject.getPassword()!=null) {
			domainObject.setEncryptedPassword(encryptorService.encryptString(domainObject.getPassword()));
		}
		User savedUser = em.merge(domainObject);
		em.getTransaction().commit();
		return savedUser;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(User.class, id));
		em.getTransaction().commit();
	}

}
