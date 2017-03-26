package com.springboot.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import com.springboot.domain.AbstractDomainClass;

public class CRUDServiceImpl<T extends AbstractDomainClass> implements CRUDService<T> {

	private Class<T> entityClass;
	private EntityManagerFactory emf;
	
	@PersistenceUnit
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@SuppressWarnings("unchecked")
	public CRUDServiceImpl() {
		 entityClass = (Class<T>)((ParameterizedType)(getClass().getGenericSuperclass())).getActualTypeArguments()[0];
	}
	
	@Override
	public List<?> listAll() {
		EntityManager em = getEntityManager();
		return em.createQuery("from "+entityClass.getSimpleName()).getResultList();
	}

	@Override
	public T getById(Integer id) {
		EntityManager em = getEntityManager();
		return em.find(entityClass, id);
	}

	@Override
	public T saveOrUpdate(T domainObject) {
		EntityManager em = getEntityManager();
		beginTransaction(em);
		T savedObject = em.merge(domainObject);
		commitTransaction(em);
		return savedObject;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = getEntityManager();
		beginTransaction(em);
		em.remove(em.find(entityClass, id));
		System.out.println("Delete before commit");
		commitTransaction(em);
		System.out.println("Delete after commit");
	}

	public EntityManager getEntityManager() {
		EntityManager em = emf.createEntityManager();
		return em;
	}
	
	public void beginTransaction(EntityManager em) {
		em.getTransaction().begin();
	}
	
	public void commitTransaction(EntityManager em) {
		em.getTransaction().commit();
	}
	
}
