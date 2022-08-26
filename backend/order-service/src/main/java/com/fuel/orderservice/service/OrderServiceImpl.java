package com.fuel.orderservice.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fuel.orderservice.model.Order;
import com.fuel.orderservice.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository orderRepository;

	@Override
	//create order id and save in db
	public Order addOrder(Order order) {
		
	    String id = generateID(order); 
		order.setId(id);
		
		orderRepository.save(order);
		return order;
	}
	
    //generate id for the order
	private String generateID(Order order) {
		String id = order.getPassport() + UUID.randomUUID().toString().substring(0, 5);
		return id;
	}
	

}
