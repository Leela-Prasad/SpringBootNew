package com.springboot.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springboot.config.JPAIntegrationConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JPAIntegrationConfiguration.class})
@ActiveProfiles(profiles={"jpa"})
public class ProductServiceJPAImplTest {

	private ProductService productService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	@Test
	public void testListAll() {
		assertThat(productService.listAll().size(),is(3));
	}

}
