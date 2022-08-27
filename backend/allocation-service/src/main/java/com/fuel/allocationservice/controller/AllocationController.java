package com.fuel.allocationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fuel.allocationservice.model.Stock;
import com.fuel.allocationservice.service.AllocationService;

@Controller
public class AllocationController {

	@Autowired
	AllocationService allocationService;
	
	public void sayHi(String name) {
		System.out.println("Hi Hi "+ name);
	}
	
	public Stock addStock(int octane92, int octane95, int autoDiesel , int superDiesel) {
		return allocationService.addStock(octane92, octane95, autoDiesel, superDiesel);
	}
}
