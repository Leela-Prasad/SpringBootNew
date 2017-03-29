package com.springboot.service.reposervices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.springboot.domain.Product;
import com.springboot.repositories.ProductRepository;
import com.springboot.service.ProductService;

@Service
@Profile({"springdatajpa"})
public class ProductServiceRepoJPAImpl implements ProductService {

	private ProductRepository productRepository;
	
	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository=productRepository;
	}
	
	@Override
	public List<Product> listAll() {
		List<Product> products = new ArrayList<>(); 
		productRepository.findAll().forEach(product -> products.add(product));
		return products;
	}

	@Override
	public Product getById(Integer id) {
		return productRepository.findOne(id);
	}

	@Override
	public Product saveOrUpdate(Product domainObject) {
		return productRepository.save(domainObject);
	}

	@Override
	public void delete(Integer id) {
		productRepository.delete(id);
	}

}
