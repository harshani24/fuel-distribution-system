package com.fuel.allocationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fuel.allocationservice.model.Stock;
import com.fuel.allocationservice.service.AllocationService;
import com.fuel.allocationservice.service.Producer;
import com.fuel.orderservice.model.Order;

@RestController
public class AllocationController {

	@Autowired
	AllocationService allocationService;
	
	@Autowired
	Producer producer;
	
	@RequestMapping("/")
	public List<Stock> findAllStockDesc() {
		return allocationService.findAllStockDesc();
	}
	
	
	public Stock addStock(int octane92, int octane95, int autoDiesel , int superDiesel) {
		return allocationService.addStock(octane92, octane95, autoDiesel, superDiesel);
	}

	public void orderAllocation(Order order) {
		boolean available = allocationService.orderAllocation(order);
		
		if(available) {
			producer.publishCompletionOfAllocation(order);
		}
		
		else {
			producer.publishRejectionOfAllocation(order);
		}
		
	}
}
