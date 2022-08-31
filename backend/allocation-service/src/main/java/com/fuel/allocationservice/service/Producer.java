package com.fuel.allocationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fuel.orderservice.model.Order;

@Service
public class Producer {

	public static final String completionTopic = "allocation-complete-topic";
	public static final String rejectionTopic = "allocation-reject-topic";
	
	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	public void publishCompletionOfAllocation(Order order) {
		kafkaTemplate.send(completionTopic, order );
		System.out.println("Publish allocation status to allocation-complete-topic from allocation service " + order);
	}
	
	public void publishRejectionOfAllocation(Order order) {
		kafkaTemplate.send(rejectionTopic, order );
		System.out.println("Publish allocation status to allocation-rejection-topic from allocation service " + order);
	}
}
