package com.fuel.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fuel.orderservice.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

}
