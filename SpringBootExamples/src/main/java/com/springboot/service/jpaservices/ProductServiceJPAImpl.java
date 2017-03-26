package com.springboot.service.jpaservices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.springboot.domain.Product;
import com.springboot.service.CRUDServiceImpl;
import com.springboot.service.ProductService;

@Service
@Profile("jpa")
public class ProductServiceJPAImpl extends CRUDServiceImpl<Product> implements ProductService{

	
}
