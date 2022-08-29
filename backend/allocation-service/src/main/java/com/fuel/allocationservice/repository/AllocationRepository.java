package com.fuel.allocationservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fuel.allocationservice.model.Stock;

public interface AllocationRepository extends MongoRepository<Stock, String>{

	List<Stock> findAllByOrderByDateTimeAsc();
	
	List<Stock> findAllByOrderByDateTimeDesc();
	
	Stock findFirstByOrderByDateTimeDesc();
	
	List<Stock> findTop5ByOrderByDateTimeDesc();
	
	
	
	Stock findByOrderId(String orderId);

}
