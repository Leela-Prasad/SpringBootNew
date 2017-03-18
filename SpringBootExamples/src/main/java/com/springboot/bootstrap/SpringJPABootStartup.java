package com.springboot.bootstrap;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.springboot.domain.Customer;
import com.springboot.domain.Product;
import com.springboot.service.CustomerService;
import com.springboot.service.ProductService;

@Component
public class SpringJPABootStartup implements ApplicationListener<ContextRefreshedEvent>{

	private ProductService productService;
	private CustomerService customerService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadProducts();
		loadCustomers();
	}
	
	public void loadProducts() {
		
		Product product1 = new Product();
		product1.setDescription("Product 1");
		product1.setPrice(new BigDecimal("12.37"));
		product1.setImageUrl("http://example.com/product1");
		productService.saveOrUpdate(product1);
		
		Product product2 = new Product();
		product2.setDescription("Product 2");
		product2.setPrice(new BigDecimal("18.56"));
		product2.setImageUrl("http://example.com/product2");
		productService.saveOrUpdate(product2);
		
		Product product3 = new Product();
		product3.setDescription("Product 3");
		product3.setPrice(new BigDecimal("20.80"));
		product3.setImageUrl("http://example.com/product3");
		productService.saveOrUpdate(product3);
		
	}

	public void loadCustomers() {
		Customer customer1 = new Customer();
		customer1.setFirstName("firstName1");
		customer1.setLastName("lastName1");
		customer1.setEmailId("emailId1");
		customer1.setAddressLine1("line1");
		customer1.setAddressLine2("line2");
		customer1.setCity("city1");
		customer1.setState("state1");
		customer1.setPhoneNumber("phoneNumber1");
		customer1.setZipcode("zipcode1");
		customerService.saveOrUpdate(customer1);
		
		Customer customer2 = new Customer();
		customer2.setFirstName("firstName2");
		customer2.setLastName("lastName2");
		customer2.setEmailId("emailId2");
		customer2.setAddressLine1("line1");
		customer2.setAddressLine2("line2");
		customer2.setCity("city2");
		customer2.setState("state2");
		customer2.setPhoneNumber("phoneNumber2");
		customer2.setZipcode("zipcode2");
		customerService.saveOrUpdate(customer2);
		
		Customer customer3 = new Customer();
		customer3.setFirstName("firstName3");
		customer3.setLastName("lastName3");
		customer3.setEmailId("emailId3");
		customer3.setAddressLine1("line1");
		customer3.setAddressLine2("line2");
		customer3.setCity("city3");
		customer3.setState("state3");
		customer3.setPhoneNumber("phoneNumber3");
		customer3.setZipcode("zipcode3");
		customerService.saveOrUpdate(customer3);
	}
	
}
