package com.fuel.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fuel.orderservice.model.Order;
import com.fuel.orderservice.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;

	@PostMapping("/orders")
	public Order addOrder(@RequestBody Order order) {
		
		Order o = orderService.addOrder(order);
		return o;
	}
	
	@GetMapping("/orders/{id}")
	public Order getOrderStatus(@PathVariable String id) {
		
		return null;
	}
	
	@GetMapping("/orders")
	public List<Order> viewallOrders() {
		return null;
	}
	
	@PutMapping("/orders/{id}")
	public String  receivedConfirm(@PathVariable String id) {
		return null;	
	}
}
