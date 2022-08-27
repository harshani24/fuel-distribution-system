package com.fuel.allocationservice.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.fuel.allocationservice.model.Stock;
import com.fuel.allocationservice.repository.AllocationRepository;

//import static org.springframework.data.mongodb.core.query.Criteria.*;
//import static org.springframework.data.mongodb.core.query.Query.*;

//import org.springframework.data.mongodb.core.query.Query;

@Service
public class AllocationServiceImpl implements AllocationService{
	
	@Autowired
	AllocationRepository allocationRepository;

	@Override
	public Stock addStock(int octane92, int octane95, int autoDiesel, int superDiesel) {
		List<Stock> stock1 = getLastStockRecord();
		System.out.println(stock1);
		
		Stock stock = new Stock();
		
		stock.setOrderId("00000");
		stock.setDate(LocalDate.now());
		stock.setTime(LocalTime.now());
		stock.setDateTime(LocalDateTime.now());
		
		
		
		allocationRepository.save(stock);
		
		return stock;
		
	}

	public List<Stock> getLastStockRecord() {
		return allocationRepository.findAllByOrderByDateAsc();
	}


	
}
