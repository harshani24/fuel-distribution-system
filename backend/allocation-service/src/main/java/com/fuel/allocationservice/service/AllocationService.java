package com.fuel.allocationservice.service;

import java.util.List;

import com.fuel.allocationservice.model.Stock;
import com.fuel.orderservice.model.Order;

public interface AllocationService {

	Stock addStock(int octane92, int octane95, int autoDiesel , int superDiesel);

	List<Stock> findAllDateAsc();
	
	String orderAllocation(Order order);

}