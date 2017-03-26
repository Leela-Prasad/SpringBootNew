package com.springboot.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.springboot.enums.OrderStatus;

@Entity
public class Orders extends AbstractDomainClass {
	
	@OneToOne
	private Customer customer;
	
	@Embedded
	private Address shippingAddress;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="order",orphanRemoval=true)
	private List<OrderDetail> orderDetails = new ArrayList<>();
	
	private OrderStatus orderStatus;
	
	private Date dateShipped;
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getDateShipped() {
		return dateShipped;
	}

	public void setDateShipped(Date dateShipped) {
		this.dateShipped = dateShipped;
	}

	public void addOrderDetail(OrderDetail orderDetail) {
		orderDetail.setOrder(this);
		orderDetails.add(orderDetail);
	}
	
	public void removeOrderDetail(OrderDetail orderDetail) {
		orderDetail.setOrder(null);
		orderDetails.remove(orderDetail);
	}
}
