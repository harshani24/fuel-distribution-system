package com.fuel.orderservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fuel.orderservice.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

	Order findOrderById(String orderId);
	
	List<Order> findAllByPassport(String passport);
}
