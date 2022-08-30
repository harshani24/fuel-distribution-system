package com.fuel.allocationservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fuel.allocationservice.model.OrderAllocation;
import com.fuel.allocationservice.model.Stock;

public interface OrderAllocationRepository extends MongoRepository<OrderAllocation, String>{

	OrderAllocation findByOrderId(String orderId);
}