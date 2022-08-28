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
		Stock stock = getLastStockRecord();
		
		//Very First One
		if(stock == null) {
			Stock initialStock = new Stock();
			
			initialStock.setOrderId("00000");
			initialStock.setDate(LocalDate.now());
			initialStock.setTime(LocalTime.now());
			initialStock.setDateTime(LocalDateTime.now());
			
			initialStock.setAvailableOcatane92(octane92);
			initialStock.setAvailableOcatane95(octane95);
			initialStock.setAvailableAutoDiesel(autoDiesel);
			initialStock.setAvailableSuperDiesel(superDiesel);
			
			allocationRepository.save(initialStock);
			
			
			return  initialStock;
		}
		
		//Add a new stock to available stocks
		else {
			Stock newStock = stock.clone();
			newStock.setOrderId("00000");
			newStock.setDate(LocalDate.now());
			newStock.setTime(LocalTime.now());
			newStock.setDateTime(LocalDateTime.now());
			
			newStock.setAvailableOcatane92(stock.getAvailableOcatane92()+octane92);
			newStock.setAvailableOcatane95(stock.getAvailableOcatane95()+octane95);
			newStock.setAvailableAutoDiesel(stock.getAvailableAutoDiesel()+autoDiesel);
			newStock.setAvailableSuperDiesel(stock.getAvailableSuperDiesel()+superDiesel);
			
			allocationRepository.save(newStock);
			
			return newStock;
		}	

	}

	public Stock getLastStockRecord() {
	  Stock stock = allocationRepository.findFirstByOrderByDateTimeDesc();
	  
	  if(stock == null) {
		  return null;
	  }
	  
	  else {
		  return stock;
	  }
	}

//	@Override
//	public List<Stock> findAllDateAsc() {
//	   // return allocationRepository.findAllByOrderByDateTimeAsc();
//		return allocationRepository.findAllByOrderByDateTimeDesc();
//	}
	
	
	@Override
	public List<Stock> findAllDateAsc() {

		//return allocationRepository.findFirstByOrderByDateTimeAsc();
		return allocationRepository.findAllByOrderByDateTimeDesc();
	}

	
}
