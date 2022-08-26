package com.fuel.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fuel.orderservice.OrderserviceApplication;
import com.fuel.orderservice.model.Order;


@Service
public class Producer {
	public static final String topic = "allocation-topic";
	
	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	public void publishToAllocationTopic(Order order) {
		kafkaTemplate.send(topic, order);
		OrderserviceApplication.logger.info("OrderService:: Publish Order("+ order.getId()+ ") to Allocation Topic");
	}


}
