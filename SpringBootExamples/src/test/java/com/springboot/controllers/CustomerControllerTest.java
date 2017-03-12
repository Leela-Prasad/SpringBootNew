package com.springboot.controllers;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springboot.domain.Customer;
import com.springboot.service.CustomerService;


public class CustomerControllerTest {

	@Mock
	private CustomerService customerService;
	
	@InjectMocks
	private CustomerController customerController;
	
	private MockMvc mockMvc;
	
	@Captor
	private ArgumentCaptor<Customer> customerCaptor;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}
	
	@Test
	public void testListCustomers() throws Exception{
		
		Customer customer1 = new Customer();
		Customer customer2 = new Customer();
		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer1);
		customerList.add(customer2);
		when(customerService.listAll()).thenReturn((List)customerList);
		mockMvc.perform(get("/customer/list"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("customer/customers"))
			   .andExpect(model().attribute("customers", hasSize(2)));
	}
	
	@Test
	public void testGetCustomer() throws Exception {
		
		when(customerService.getById(anyInt())).thenReturn(new Customer());
		mockMvc.perform(get("/customer/show/1"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("customer/customer"))
			   .andExpect(model().attribute("customer", instanceOf(Customer.class)));
	}
	
	@Test
	public void testNewCustomer() throws Exception {
		verifyZeroInteractions(customerService);
		mockMvc.perform(get("/customer/new"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("customer/customerform"))
			   .andExpect(model().attribute("customer", instanceOf(Customer.class)));
		
	}
	
	@Test
	public void testSaveOrUpdate() throws Exception {
		Integer id=1;
		String firstName="leela";
		String lastName="jagu";
		String emailId="leela.jagu@gmail.com";
		String phoneNumber="9176552929";
		String addressLine1="line1";
		String addressLine2="linee2";
		String city="Vijayawada";
		String state="AP";
		String zipcode="520001";
		
		Customer saveorUpdateObject=new Customer();
		saveorUpdateObject.setId(id);
		saveorUpdateObject.setFirstName(firstName);
		saveorUpdateObject.setLastName(lastName);
		saveorUpdateObject.setEmailId(emailId);
		saveorUpdateObject.setPhoneNumber(phoneNumber);
		saveorUpdateObject.setAddressLine1(addressLine1);
		saveorUpdateObject.setAddressLine2(addressLine2);
		saveorUpdateObject.setCity(city);
		saveorUpdateObject.setState(state);
		saveorUpdateObject.setZipcode(zipcode);
		
		when(customerService.saveOrUpdate(any(Customer.class))).thenReturn(saveorUpdateObject);
		mockMvc.perform(post("/customer")
				.param("id", "1")
				.param("firstName", firstName)
				.param("lastName", lastName)
				.param("emailId", emailId)
				.param("phoneNumber", phoneNumber)
				.param("addressLine1", addressLine1)
				.param("addressLine2", addressLine2)
				.param("city", city)
				.param("state", state)
				.param("zipcode", zipcode))
			    .andExpect(status().is3xxRedirection())
			    .andExpect(view().name("redirect:/customer/show/1"))
			    .andExpect(model().attribute("customer", instanceOf(Customer.class)))
			    .andExpect(model().attribute("customer", hasProperty("id",is(id))))
			    .andExpect(model().attribute("customer", hasProperty("firstName",is(firstName))))
			    .andExpect(model().attribute("customer", hasProperty("lastName",is(lastName))))
			    .andExpect(model().attribute("customer", hasProperty("emailId",is(emailId))))
			    .andExpect(model().attribute("customer", hasProperty("phoneNumber",is(phoneNumber))))
			    .andExpect(model().attribute("customer", hasProperty("addressLine1",is(addressLine1))))
			    .andExpect(model().attribute("customer", hasProperty("addressLine2",is(addressLine2))))
			    .andExpect(model().attribute("customer", hasProperty("city",is(city))))
			    .andExpect(model().attribute("customer", hasProperty("state",is(state))))
			    .andExpect(model().attribute("customer", hasProperty("zipcode",is(zipcode))));
			    
			verify(customerService).saveOrUpdate(customerCaptor.capture()); 
			assertThat(saveorUpdateObject.getId(),is(customerCaptor.getValue().getId()));
			assertThat(saveorUpdateObject.getFirstName(),is(customerCaptor.getValue().getFirstName()));
			assertThat(saveorUpdateObject.getLastName(),is(customerCaptor.getValue().getLastName()));
			assertThat(saveorUpdateObject.getEmailId(),is(customerCaptor.getValue().getEmailId()));
			assertThat(saveorUpdateObject.getPhoneNumber(),is(customerCaptor.getValue().getPhoneNumber()));
			assertThat(saveorUpdateObject.getAddressLine1(),is(customerCaptor.getValue().getAddressLine1()));
			assertThat(saveorUpdateObject.getAddressLine2(),is(customerCaptor.getValue().getAddressLine2()));
			assertThat(saveorUpdateObject.getCity(),is(customerCaptor.getValue().getCity()));
			assertThat(saveorUpdateObject.getState(),is(customerCaptor.getValue().getState()));
			assertThat(saveorUpdateObject.getZipcode(),is(customerCaptor.getValue().getZipcode()));
		
	}
	
	@Test
	public void testEdit() throws Exception {
		when(customerService.getById(anyInt())).thenReturn(new Customer());
		
		mockMvc.perform(get("/customer/edit/1"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("customer/customerform"))
			   .andExpect(model().attribute("customer", instanceOf(Customer.class)));
	}
	
	@Test
	public void testDelete() throws Exception {
		
		mockMvc.perform(get("/customer/delete/1"))
			   .andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/customer/list"));
		
		verify(customerService,times(1)).delete(anyInt());
			   
	}

}
