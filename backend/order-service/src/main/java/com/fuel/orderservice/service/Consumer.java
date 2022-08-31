package com.fuel.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fuel.orderservice.controller.OrderController;
import com.fuel.orderservice.model.Order;

@Service
public class Consumer {

	@Autowired
	OrderController orderController;
	
	@KafkaListener(topics= "allocation-complete-topic",  groupId = "order-group")
	public void listenAllocationCompleteTopic(Order order) {
		System.out.println("Listen to allocation-complete-topic in OrderService from AllocationService " + order);
		
		String status="allocated";
		orderController.changeAllocationStatusToAllocate(order , status);
	}
	
	@KafkaListener(topics= "allocation-reject-topic", groupId = "order-group")
	public void listenAllocationRejectTopic(Order order) {
		System.out.println("Listen to allocation-reject-topic in OrderService from AllocationService " + order);
		
		String status = "rejected";
		orderController.changeAllocationStatusToReject(order, status);
	}	
}
