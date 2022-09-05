package com.fuel.scheduleservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fuel.orderservice.model.Order;

@Service
public class Producer {

	public static final String SCHEDULED_COMPLETE_TOPIC = "scheduled-complete-topic";
	
	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	
	public void publishScheduledOrderDate(Order order) {
		kafkaTemplate.send(SCHEDULED_COMPLETE_TOPIC, order);
		System.out.println("Publish scheduled date to scheduled-complete-topic from scheduled service " + order);
	}

}
