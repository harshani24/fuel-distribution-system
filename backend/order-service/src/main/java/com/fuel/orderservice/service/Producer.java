package com.fuel.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fuel.orderservice.OrderserviceApplication;
import com.fuel.orderservice.model.Order;


@Service
public class Producer {
	public static final String ALLOCATION_TOPIC = "allocation-topic";
	public static final String SCHEDULE_TOPIC = "schedule-topic";
	
	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	public void publishToAllocationTopic(Order order) {
		kafkaTemplate.send(ALLOCATION_TOPIC, order);
		System.out.println("Publish order to allocation topic from order service " + order);
		OrderserviceApplication.logger.info("OrderService:: Publish Order("+ order.getId()+ ") to Allocation Topic");
	}
	
	public void publishToScheduleTopic(Order order) {
		kafkaTemplate.send(SCHEDULE_TOPIC, order);
		System.out.println("Publish order to schedule topic from order service " + order);
		OrderserviceApplication.logger.info("OrderService:: Publish Order("+ order.getId()+ ") to Schedule Topic");
	}


}
