package com.fuel.orderservice.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
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

	@Override
	//get one order
	public Order getOrder(String id) {
		Optional<Order> order = orderRepository.findById(id);
		
		if(order.isPresent()) {
		 return order.get();
		}
		return null;
		
	}
	
	@Override
	//get order status
	public String getOrderStatus(String id) {
	Optional<Order> order = orderRepository.findById(id);
		
		if(order.isPresent()) {
		 return order.get().getStatus();
		}
		return "Order id is incorrect";
	}
	
	
	@Override
	public List<Order> viewAllOrders(){
		List<Order> orders = orderRepository.findAll();
		return orders;
		
	}
	
	@Override    
	public String receivedConfirm(String id) {
		Optional<Order> order = orderRepository.findById(id);
		
		if(order.isPresent()) {
			return completeOrder(order.get());
		}
		else {
			return "There is something went wrong with order id";
		}
			
	}
	
	public String completeOrder(Order order) {
		LocalDateTime time = LocalDateTime.now() ;
	
		order.setCompleted(true);
		order.setCompletedTime(time);		
		
		order.setStatus("completed");
		order.setStatusTime(time);
		order.setStatusDate(time);
		
		orderRepository.save(order);
		
		return "Order Completed Successfully";
	}

}
