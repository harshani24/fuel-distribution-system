package com.fuel.allocationservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fuel.allocationservice.model.Stock;

public interface StockAllocationRepository extends MongoRepository<Stock, String>{

	List<Stock> findAllByOrderByTimeAsc();
	
	List<Stock> findAllByOrderByTimeDesc();
	
	Stock findFirstByOrderByTimeDesc();
	
	Stock findFirstByOrderByTimeAsc();
	
	List<Stock> findTop20ByOrderByTimeDesc();

	Stock findTopByOrderByTimeAsc();
	
	 
	
	//Stock findByOrderId(String orderId);

}
