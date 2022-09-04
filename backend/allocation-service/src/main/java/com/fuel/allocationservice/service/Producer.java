package com.fuel.allocationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fuel.orderservice.model.Order;

@Service
public class Producer {

	public static final String COMPLETION_TOPIC = "allocation-complete-topic";
	public static final String REJECTION_TOPIC = "allocation-reject-topic";
	
	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	public void publishCompletionOfAllocation(Order order) {
		kafkaTemplate.send(COMPLETION_TOPIC, order );
		System.out.println("Publish allocation status to allocation-complete-topic from allocation service " + order);
	}
	
	public void publishRejectionOfAllocation(Order order) {
		kafkaTemplate.send(REJECTION_TOPIC, order );
		System.out.println("Publish allocation status to allocation-rejection-topic from allocation service " + order);
	}
}
