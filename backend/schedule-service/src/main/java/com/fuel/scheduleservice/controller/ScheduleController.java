package com.fuel.scheduleservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fuel.orderservice.model.Order;
import com.fuel.scheduleservice.service.Producer;
import com.fuel.scheduleservice.service.ScheduleService;

@Controller
public class ScheduleController {
	
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	Producer producer;

	public void scheduleDate(Order order) {
		Order scheduledOrder =scheduleService.scheduleDate(order);
		System.out.println("scheudled order "+ scheduledOrder.getScheduledTime());

		producer.publishScheduledOrderDate(scheduledOrder);
	}
}
