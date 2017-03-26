package com.springboot.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.springboot.config.JPAIntegrationConfiguration;
import com.springboot.domain.Cart;
import com.springboot.domain.CartDetail;
import com.springboot.domain.Customer;
import com.springboot.domain.Product;
import com.springboot.domain.User;
import com.springboot.service.jpaservices.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JPAIntegrationConfiguration.class})
@ActiveProfiles({"jpa"})
public class UserServiceJPAImplTest {

	private UserService userService;
	private ProductService productService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService=userService;
	}
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService=productService;
	}
	
	@Test
	public void testSaveOrUpdate() {
		
		User user = new User();
		user.setUserName("Leela");
		user.setPassword("password");
		
		Customer customer = new Customer();
		customer.setFirstName("leela");
		customer.setLastName("jagu");

		user.setCustomer(customer);
		
		User savedUser = userService.saveOrUpdate(user);
		
		assertNotNull(savedUser.getId());
		assertNotNull(savedUser.getEncryptedPassword());
		assertNotNull(savedUser.getVersion());
		System.out.println("Password :" + savedUser.getEncryptedPassword());
		assertNotNull(savedUser.getCustomer());
		assertNotNull(savedUser.getCustomer().getId());
		
		
	}
	
	@Test
	public void testAddCartToUser() {
		
		User user = new User();
		user.setUserName("Leela");
		user.setPassword("password");
		
		Customer customer = new Customer();
		customer.setFirstName("leela");
		customer.setLastName("jagu");

		user.setCustomer(customer);
		
		user.setCart(new Cart());
		
		User savedUser = userService.saveOrUpdate(user);
		
		assertNotNull(savedUser.getId());
		assertNotNull(savedUser.getEncryptedPassword());
		assertNotNull(savedUser.getVersion());
		System.out.println("Password :" + savedUser.getEncryptedPassword());
		assertNotNull(savedUser.getCustomer());
		assertNotNull(savedUser.getCustomer().getId());
		assertNotNull(savedUser.getCart());
		assertNotNull(savedUser.getCart().getId());
		assertNotNull(savedUser.getCart().getVersion());
		
	}
	
	@Test
	public void testAddCartToUserWithCartDetails() {
		
		User user = new User();
		user.setUserName("Leela");
		user.setPassword("password");
		
		Customer customer = new Customer();
		customer.setFirstName("leela");
		customer.setLastName("jagu");

		user.setCustomer(customer);
		
		user.setCart(new Cart());
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) productService.listAll();
		
		CartDetail cartDetail1 = new CartDetail();
		cartDetail1.setProduct(products.get(0));
		
		CartDetail cartDetail2 = new CartDetail();
		cartDetail2.setProduct(products.get(1));
		
		user.getCart().addCartDetail(cartDetail1);
		user.getCart().addCartDetail(cartDetail1);
		
		
		User savedUser = userService.saveOrUpdate(user);
		
		assertNotNull(savedUser.getId());
		assertNotNull(savedUser.getEncryptedPassword());
		assertNotNull(savedUser.getVersion());
		System.out.println("Password :" + savedUser.getEncryptedPassword());
		assertNotNull(savedUser.getCustomer());
		assertNotNull(savedUser.getCustomer().getId());
		assertNotNull(savedUser.getCart());
		assertNotNull(savedUser.getCart().getId());
		assertNotNull(savedUser.getCart().getVersion());
		assertNotNull(savedUser.getCart().getCartDetails());
		assertThat(savedUser.getCart().getCartDetails().size(),is(2));
		assertNotNull(savedUser.getCart().getCartDetails().get(0).getId());
		
	}
	
	@Test
	public void testAddAndRemoveCartWithCartDetails() {
		User user = new User();
		user.setUserName("Leela");
		user.setPassword("password");
		
		Customer customer = new Customer();
		customer.setFirstName("leela");
		customer.setLastName("jagu");

		user.setCustomer(customer);
		
		user.setCart(new Cart());
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) productService.listAll();
		
		CartDetail cartDetail1 = new CartDetail();
		cartDetail1.setProduct(products.get(0));
		
		CartDetail cartDetail2 = new CartDetail();
		cartDetail2.setProduct(products.get(1));
		
		user.getCart().addCartDetail(cartDetail1);
		user.getCart().addCartDetail(cartDetail1);
		
		
		User savedUser = userService.saveOrUpdate(user);
		
		assertNotNull(savedUser.getId());
		assertNotNull(savedUser.getEncryptedPassword());
		assertNotNull(savedUser.getVersion());
		System.out.println("Password :" + savedUser.getEncryptedPassword());
		assertNotNull(savedUser.getCustomer());
		assertNotNull(savedUser.getCustomer().getId());
		assertNotNull(savedUser.getCart());
		assertNotNull(savedUser.getCart().getId());
		assertNotNull(savedUser.getCart().getVersion());
		assertNotNull(savedUser.getCart().getCartDetails());
		assertThat(savedUser.getCart().getCartDetails().size(),is(2));
		assertNotNull(savedUser.getCart().getCartDetails().get(0).getId());
		
		savedUser.getCart().removeCartDetail(savedUser.getCart().getCartDetails().get(0));
		userService.saveOrUpdate(savedUser);
		assertThat(savedUser.getCart().getCartDetails().size(),is(1));
		assertNotNull(savedUser.getCart().getCartDetails().get(0).getId());
	}

}
