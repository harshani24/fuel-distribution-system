package com.fuel.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fuel.orderservice.controller.OrderController;
import com.fuel.orderservice.dto.ScheduleDTO;
import com.fuel.orderservice.model.Order;

@Service
public class Consumer {

	@Autowired
	OrderController orderController;
	
	@KafkaListener(topics= "allocation-complete-topic",  groupId = "order-group")
	public void listenAllocationCompleteTopic(Order order) throws InterruptedException {
		System.out.println("Listen to allocation-complete-topic in OrderService from AllocationService " + order);
		System.out.println("============== " +order.getStatus());
		//Thread.sleep(10000);
		
		orderController.changeAllocationStatusToAllocate(order);
	}
	
	@KafkaListener(topics= "allocation-reject-topic", groupId = "order-group" )
	public void listenAllocationRejectTopic(Order order) throws InterruptedException {
		System.out.println("Listen to allocation-reject-topic in OrderService from AllocationService " + order);
		System.out.println("============== " +order.getStatus());
		//Thread.sleep(10000);
		
		orderController.changeAllocationStatusToReject(order);
	}	
	
	@KafkaListener(topics = "scheduled-complete-topic" , groupId = "order-group")
	public void listenScheduledCompleteTopic(Order order) throws InterruptedException {
		System.out.println("Listen to scheduled-complete-topic in OrderService from ScheduleService " + order);
		System.out.println("============== " +order.getStatus());
		//Thread.sleep(10000);
		 
		orderController.addScheduledDate(order);
	}
	
	@KafkaListener(topics = "dispatch-complete-topic" , groupId = "order-group" , containerFactory = "sKafkaListenerContainerFactory")
	public void listenDispatchCompleteTopic(String id) throws InterruptedException {
		System.out.println("Listen to dispatch-complete-topic in OrderService from DispatchService " + id);
		//Thread.sleep(10000);
		 
		orderController.changeDispatchStatus(id);
	}
}
