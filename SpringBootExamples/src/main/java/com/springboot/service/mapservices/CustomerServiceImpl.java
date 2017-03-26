package com.springboot.service.mapservices;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.springboot.domain.AbstractDomainClass;
import com.springboot.domain.Address;
import com.springboot.domain.Customer;
import com.springboot.service.CustomerService;

@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService implements CustomerService{
	
	@Override
	public void loadDomainObjects() {
		Customer customer1 = new Customer();
		customer1.setId(1);
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
		
		Customer customer2 = new Customer();
		customer2.setId(2);
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
		
		Customer customer3 = new Customer();
		customer3.setId(3);
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
		
		domainMap.put(customer1.getId(),customer1);
		domainMap.put(customer2.getId(),customer2);
		domainMap.put(customer3.getId(),customer3);
	}
	
	@Override
	public List<AbstractDomainClass> listAll() {
		return super.listAll();
	}
	
	@Override
	public Customer getById(Integer id) {
		return (Customer) super.getById(id);
	}
	
	@Override
	public  Customer saveOrUpdate(Customer domainObject) {
		return (Customer) super.saveOrUpdate(domainObject);
	}
	
	@Override
	public void delete(Integer id) {
		 super.delete(id);
	}
	
}
