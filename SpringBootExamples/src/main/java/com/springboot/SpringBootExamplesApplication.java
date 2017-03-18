package com.springboot;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.springboot.domain.Cart;
import com.springboot.domain.CartDetail;
import com.springboot.domain.Customer;
import com.springboot.domain.Product;
import com.springboot.domain.User;
import com.springboot.service.ProductService;
import com.springboot.service.jpaservices.UserService;

@SpringBootApplication
public class SpringBootExamplesApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootExamplesApplication.class, args);
		UserService userService = (UserService)context.getBean("userServiceJPAImpl");
		ProductService productService = (ProductService) context.getBean("productServiceJPAImpl");
		User user = new User();
		user.setUserName("Leela");
		user.setPassword("password");
		
		Customer customer = new Customer();
		customer.setFirstName("leela");
		customer.setLastName("jagu");

		user.setCustomer(customer);
		Cart cart = new Cart();
		cart.setUser(user);
		user.setCart(cart);
		
		List<Product> products = (List<Product>) productService.listAll();
		
		CartDetail cartDetail1 = new CartDetail();
		cartDetail1.setProduct(products.get(0));
		
		CartDetail cartDetail2 = new CartDetail();
		cartDetail2.setProduct(products.get(1));
		user.getCart().addCartDetail(cartDetail1);
		user.getCart().addCartDetail(cartDetail2);
		
		User savedUser = userService.saveOrUpdate(user);
		
		savedUser.getCart().removeCartDetail(savedUser.getCart().getCartDetails().get(0));
		userService.saveOrUpdate(savedUser);
		
		
		
		
	}
}
