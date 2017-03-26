package com.springboot.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Column;

@Entity
public class Customer extends AbstractDomainClass{
	
	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNumber;
	@Embedded
	private Address billingAddress;
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="addressLine1",column=@Column(name="shipping_address_line1")),
		@AttributeOverride(name="addressLine2",column=@Column(name="shipping_address_line2")),
		@AttributeOverride(name="city",column=@Column(name="shipping_city")),
		@AttributeOverride(name="state",column=@Column(name="shipping_state")),
		@AttributeOverride(name="zipcode",column=@Column(name="shipping_zipcode"))
	})
	
	private Address shippingAddress;
	
	@OneToOne
	private User user;
	
	/*@OneToOne
	private Orders order;*/
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
		
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	/*public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}*/
	
}
