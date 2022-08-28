package com.fuel.allocationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fuel.allocationservice.model.Stock;
import com.fuel.allocationservice.service.AllocationService;

@RestController
public class AllocationController {

	@Autowired
	AllocationService allocationService;
	
	@RequestMapping("/")
	public List<Stock> sayHi(String name) {
		return allocationService.findAllDateAsc();
	}
	
	public Stock addStock(int octane92, int octane95, int autoDiesel , int superDiesel) {
		return allocationService.addStock(octane92, octane95, autoDiesel, superDiesel);
	}
}
