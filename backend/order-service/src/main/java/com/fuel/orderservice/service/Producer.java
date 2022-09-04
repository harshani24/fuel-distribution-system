package com.fuel.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fuel.orderservice.OrderserviceApplication;
import com.fuel.orderservice.dto.ScheduleDTO;
import com.fuel.orderservice.model.Order;


@Service
public class Producer {
	public static final String ALLOCATION_TOPIC = "allocation-topic";
	public static final String SCHEDULE_TOPIC = "schedule-topic";
	public static final String DISPATCH_TOPIC = "dispatch-topic";
	
	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplateOrder;
	
	@Autowired
	private KafkaTemplate<String, ScheduleDTO> kafkaTemplateSchedule;
	
	
	public void publishToAllocationTopic(Order order) {
		kafkaTemplateOrder.send(ALLOCATION_TOPIC, order);
		System.out.println("Publish order to allocation topic from order service " + order);
		OrderserviceApplication.logger.info("OrderService:: Publish Order("+ order.getId()+ ") to Allocation Topic");
	}
	
	public void publishToScheduleTopic(Order order) {
		kafkaTemplateOrder.send(SCHEDULE_TOPIC, order);
		System.out.println("Publish order to schedule topic from order service " + order);
		OrderserviceApplication.logger.info("OrderService:: Publish Order("+ order.getId()+ ") to Schedule Topic");
	}
	
	public void publishToDispatchTopic(ScheduleDTO scheduleDTO) {
		
		kafkaTemplateSchedule.send(DISPATCH_TOPIC, scheduleDTO);
		System.out.println("Publish order to dispatch topic from order service " + scheduleDTO);
		OrderserviceApplication.logger.info("OrderService:: Publish Order("+ scheduleDTO.getOrderId()+ ") to Dispatch Topic");
	}


}
