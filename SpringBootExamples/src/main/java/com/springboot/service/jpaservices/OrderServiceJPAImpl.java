package com.springboot.service.jpaservices;

import org.springframework.stereotype.Service;

import com.springboot.domain.Orders;
import com.springboot.service.CRUDServiceImpl;

@Service
public class OrderServiceJPAImpl extends CRUDServiceImpl<Orders> implements OrderService {

}
