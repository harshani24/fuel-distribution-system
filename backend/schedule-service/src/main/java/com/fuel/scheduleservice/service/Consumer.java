package com.fuel.scheduleservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fuel.orderservice.model.Order;
import com.fuel.scheduleservice.controller.ScheduleController;

@Service
public class Consumer {
	
	@Autowired
	ScheduleController scheduleController;

	@KafkaListener(topics = "schedule-topic", groupId = "schedule-group")
	public void listenScheduleTopic(Order order) throws InterruptedException {
		System.out.println("Listen to schedule topic in ScheduleService from OrderService " + order);
		System.out.println("============== " +order.getStatus());
		
		//Thread.sleep(10000);
		scheduleController.scheduleDate(order);
	}
}
