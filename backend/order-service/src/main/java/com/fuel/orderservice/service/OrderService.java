package com.fuel.orderservice.service;

import java.util.List;

import com.fuel.orderservice.dto.ScheduleDTO;
import com.fuel.orderservice.model.Order;

public interface OrderService {

	Order addOrder(Order order);

	Order getOrder(String id);

	String getOrderStatus(String id);
	
	List<Order> viewAllOrders();
	
	String receivedConfirm(String id);

	Order changeAllocationStatus(Order order);
	
	ScheduleDTO addScheduledDate(Order order);

}
