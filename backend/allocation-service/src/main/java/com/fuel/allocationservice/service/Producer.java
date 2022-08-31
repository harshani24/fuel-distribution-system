package com.fuel.allocationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

	public static final String allocationCompleteTopic = "schedule-topic";
	public static final String allocationRejectTopic = "order-allocation-reject-topic";
	
	@Autowired
	private KafkaTemplate<String, String > kafkaTemplate;
	
	public void publishCompletionOfAllocation() {
		
	}
	
	public void publishRejectionOfAllocation() {
		
	}
}
