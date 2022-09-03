package com.fuel.scheduleservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fuel.orderservice.model.Order;

@Service
public class Consumer {

	@KafkaListener(topics = "schedule-topic", groupId = "schedule-group")
	public void listenScheduleTopic(Order order) {
		System.out.println("Listen to schedule topic in ScheduleService from OrderService " + order);
		System.out.println("============== " +order.getStatus());
	}
}
