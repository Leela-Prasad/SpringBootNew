package com.springboot.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.springboot.model.Product;

@Service
public class ProductServiceImpl implements ProductService{

	private Map<Integer,Product> products=new HashMap<>();
	
	public ProductServiceImpl() {
		loadProducts();
	}
	
	private void  loadProducts() {
		
		Product product1 = new Product();
		product1.setId(1);
		product1.setDescription("Product 1");
		product1.setPrice(new BigDecimal("12.37"));
		product1.setImageUrl("http://example.com/product1");
		
		Product product2 = new Product();
		product2.setId(2);
		product2.setDescription("Product 2");
		product2.setPrice(new BigDecimal("18.56"));
		product2.setImageUrl("http://example.com/product2");
		
		Product product3 = new Product();
		product3.setId(3);
		product3.setDescription("Product 3");
		product3.setPrice(new BigDecimal("20.80"));
		product3.setImageUrl("http://example.com/product3");
		
		products.put(product1.getId(), product1);
		products.put(product2.getId(), product2);
		products.put(product3.getId(), product3);
	}
	
	@Override
	public List<Product> getProducts() {
		List<Product> listOfProducts = new ArrayList<>();
		listOfProducts.addAll(products.values());
		return listOfProducts;	
	}

	@Override
	public Product getProductById(Integer id) {
		return products.get(id);
	}

	@Override
	public Product saveOrUpdateProduct(Product product) {
		if(product==null) {
			throw new NullPointerException("Product cannot be NULL");
		}
		if(product.getId()==null) {
			product.setId(Collections.max(products.keySet())+1);
		}
		products.put(product.getId(),product);
		return product;
	}
	
	@Override
	public void deleteProduct(Integer id) {
		products.remove(id);	
	}
	
}
