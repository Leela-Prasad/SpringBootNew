package com.springboot.bootstrap;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.springboot.domain.Address;
import com.springboot.domain.Cart;
import com.springboot.domain.CartDetail;
import com.springboot.domain.Customer;
import com.springboot.domain.OrderDetail;
import com.springboot.domain.Orders;
import com.springboot.domain.Product;
import com.springboot.domain.Role;
import com.springboot.domain.User;
import com.springboot.enums.OrderStatus;
import com.springboot.service.ProductService;
import com.springboot.service.jpaservices.OrderService;
import com.springboot.service.jpaservices.RoleService;
import com.springboot.service.jpaservices.UserService;


@Component
public class SpringJPABootStrap implements ApplicationListener<ContextRefreshedEvent>{

	private ProductService productService;
	private UserService userService;
	private OrderService orderService;
	private RoleService roleService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService=userService;
	}
	
	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService=roleService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadProducts();
		loadUsersAndCustomers();
		loadCarts();
		loadOrders();
		loadRoles();
		assignUsersToDefaultRole();
		assignUsersToAdminRole();
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
	
	public void loadUsersAndCustomers() {
		
		User user1 = new User();
		
		user1.setUserName("user1");
		user1.setPassword("password");
		
		Customer customer1 = new Customer();
		customer1.setFirstName("firstName1");
		customer1.setLastName("lastName1");
		customer1.setEmailId("emailId1");
		customer1.setBillingAddress(new Address());
		customer1.getBillingAddress().setAddressLine1("line1");
		customer1.getBillingAddress().setAddressLine2("line2");
		customer1.getBillingAddress().setCity("city1");
		customer1.getBillingAddress().setState("state1");
		customer1.getBillingAddress().setZipcode("zipcode1");
		customer1.setPhoneNumber("phoneNumber1");
		
		user1.setCustomer(customer1);
		userService.saveOrUpdate(user1);
		
		User user2 = new User();
		
		user2.setUserName("user2");
		user2.setPassword("password");
		
		Customer customer2 = new Customer();
		customer2.setFirstName("firstName2");
		customer2.setLastName("lastName2");
		customer2.setEmailId("emailId2");
		customer2.setBillingAddress(new Address());
		customer2.getBillingAddress().setAddressLine1("line1");
		customer2.getBillingAddress().setAddressLine2("line2");
		customer2.getBillingAddress().setCity("city2");
		customer2.getBillingAddress().setState("state2");
		customer2.getBillingAddress().setZipcode("zipcode2");
		customer2.setPhoneNumber("phoneNumber2");
		
		user2.setCustomer(customer2);
		userService.saveOrUpdate(user2);
		
		User user3 = new User();
		
		user3.setUserName("user3");
		user3.setPassword("password");
		
		Customer customer3 = new Customer();
		customer3.setFirstName("firstName3");
		customer3.setLastName("lastName3");
		customer3.setEmailId("emailId3");
		customer3.setBillingAddress(new Address());
		customer3.getBillingAddress().setAddressLine1("line1");
		customer3.getBillingAddress().setAddressLine2("line2");
		customer3.getBillingAddress().setCity("city3");
		customer3.getBillingAddress().setState("state3");
		customer3.getBillingAddress().setZipcode("zipcode3");
		customer3.setPhoneNumber("phoneNumber3");
		
		user3.setCustomer(customer3);
		userService.saveOrUpdate(user3);
	}
	
	public void loadCarts() {
		
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>)userService.listAll();
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>)productService.listAll();
		
		for(User user : users) {
			user.setCart(new Cart());
			for(Product product : products) {
				CartDetail cartDetail = new CartDetail();
				cartDetail.setProduct(product);
				cartDetail.setQuantity(1);
				user.getCart().addCartDetail(cartDetail);
			} 
			userService.saveOrUpdate(user);
		}
	}
	
	public void loadOrders() {
		
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>)userService.listAll();
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>)productService.listAll();
		
		for(User user : users) {
			Orders order = new Orders();
			order.setCustomer(user.getCustomer());
			order.setOrderStatus(OrderStatus.SHIPPED);
			
			for(Product product : products) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setProduct(product);
				orderDetail.setQuantity(1);
				order.addOrderDetail(orderDetail);
			}
			orderService.saveOrUpdate(order);
		}
	}
	
	public void loadRoles() {
		Role role = new Role();
		role.setRole("CUSTOMER");
		roleService.saveOrUpdate(role);
	}
	
	public void assignUsersToDefaultRole() {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) userService.listAll();
		@SuppressWarnings("unchecked")
		List<Role> roles = (List<Role>) roleService.listAll();
		/*for(Role arole : roles) {
			for(User auser : users) {
				auser.addRole(arole);
				userService.saveOrUpdate(auser);
			}
		}*/
		
		roles.forEach(arole -> {
			users.forEach(auser -> {
				auser.addRole(arole);
				userService.saveOrUpdate(auser);
			});
		});
		
		
	}
	
	public void assignUsersToAdminRole() {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) userService.listAll();
		
			
		Role role = new Role();
		role.setRole("admin");
		roleService.saveOrUpdate(role);
		
			users.forEach(auser -> {
				if(auser.getUserName().equalsIgnoreCase("user3")) {		
					User user = new User();
					user=auser;
					user.addRole(role);
					role.addUser(user);
					userService.saveOrUpdate(user);

				}});
		
		
	}
}
