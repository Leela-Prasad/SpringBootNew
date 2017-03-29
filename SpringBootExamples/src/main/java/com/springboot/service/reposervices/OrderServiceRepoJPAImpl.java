package com.springboot.service.reposervices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.springboot.domain.Orders;
import com.springboot.repositories.OrderRepository;
import com.springboot.service.jpaservices.OrderService;

@Service
@Profile({"springdatajpa"})
public class OrderServiceRepoJPAImpl implements OrderService {

	private OrderRepository orderRepository;
	
	@Autowired
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository=orderRepository;
	}

	@Override
	public List<Orders> listAll() {
		List<Orders> orders = new ArrayList<>();
		orderRepository.findAll().forEach(orders::add);
		return orders;
	}

	@Override
	public Orders getById(Integer id) {
		return orderRepository.findOne(id);
	}

	@Override
	public Orders saveOrUpdate(Orders domainObject) {
		return orderRepository.save(domainObject);
	}

	@Override
	public void delete(Integer id) {
		orderRepository.delete(id);
	}

	
}
