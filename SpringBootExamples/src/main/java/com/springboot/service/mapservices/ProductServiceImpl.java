package com.springboot.service.mapservices;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.springboot.domain.AbstractDomainClass;
import com.springboot.domain.Product;
import com.springboot.service.ProductService;


@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService{
	
	public void  loadDomainObjects() {
		
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
		
		domainMap.put(product1.getId(), product1);
		domainMap.put(product2.getId(), product2);
		domainMap.put(product3.getId(), product3);
	}
	
	@Override
	public List<AbstractDomainClass> listAll() {
		return super.listAll();	
	}

	@Override
	public Product getById(Integer id) {
		return (Product)super.getById(id);
	}

	@Override
	public Product saveOrUpdate(Product product) {
		return (Product)super.saveOrUpdate(product);
	}
	
	@Override
	public void delete(Integer id) {
		super.delete(id);	
	}
	
}