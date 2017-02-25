package com.springboot.services;

import java.util.List;

import com.springboot.model.Product;

public interface ProductService {

	public List<Product> getProducts();
	
	public Product getProductById(Integer id);
	
	public Product saveOrUpdateProduct(Product product);
	
	public void deleteProduct(Integer id);
	
}
