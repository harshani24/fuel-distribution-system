package com.fuel.allocationservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fuel.allocationservice.model.Stock;

public interface AllocationRepository extends MongoRepository<Stock, String>{

	List<Stock> findAllByOrderByDateAsc();

}
