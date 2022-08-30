package com.fuel.allocationservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuel.allocationservice.model.OrderAllocation;
import com.fuel.allocationservice.model.Stock;
import com.fuel.allocationservice.repository.OrderAllocationRepository;
import com.fuel.allocationservice.repository.StockAllocationRepository;
import com.fuel.orderservice.model.Order;


@Service
public class AllocationServiceImpl implements AllocationService{
	
	@Autowired
	StockAllocationRepository stockAllocationRepository;
	
	@Autowired
	OrderAllocationRepository orderAllocationRepository;

	@Override
	public Stock addStock(int octane92, int octane95, int autoDiesel, int superDiesel) {
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		Stock stock = getLastStockRecord();
		
		//Very First One
		if(stock == null) {
			Stock initialStock = new Stock();
			
			initialStock.setOrderId("00000");
			initialStock.setOrderAllocationId("none");
			initialStock.setDate(currentDateTime);
			initialStock.setTime(currentDateTime);
			initialStock.setDateTime(currentDateTime);
			
			initialStock.setAvailableOcatane92(octane92);
			initialStock.setAvailableOcatane95(octane95);
			initialStock.setAvailableAutoDiesel(autoDiesel);
			initialStock.setAvailableSuperDiesel(superDiesel);
			
			initialStock.setStatusOcatane92("stocked");
			initialStock.setStatusOcatane95("stocked");
			initialStock.setStatusAutoDiesel("stocked");
			initialStock.setStatusSuperDiesel("stocked");
			
			stockAllocationRepository.save(initialStock);
			
			return  initialStock;
		}
		
		//Add a new stock to available stocks
		else {
			Stock newStock = stock.clone();
			
			newStock.setOrderId("00000");
			newStock.setOrderAllocationId("none");

			newStock.setDate(currentDateTime);
			newStock.setTime(currentDateTime);
			newStock.setDateTime(currentDateTime);
			
			newStock.setAvailableOcatane92(stock.getAvailableOcatane92()+octane92);
			newStock.setAvailableOcatane95(stock.getAvailableOcatane95()+octane95);
			newStock.setAvailableAutoDiesel(stock.getAvailableAutoDiesel()+autoDiesel);
			newStock.setAvailableSuperDiesel(stock.getAvailableSuperDiesel()+superDiesel);
			
			newStock.setStatusOcatane92("stocked");
			newStock.setStatusOcatane95("stocked");
			newStock.setStatusAutoDiesel("stocked");
			newStock.setStatusSuperDiesel("stocked");
			
			stockAllocationRepository.save(newStock);
			
			return newStock;
		}	

	}
	
	public Stock getLastStockRecord() {
		  Stock stock = stockAllocationRepository.findFirstByOrderByDateTimeDesc();
		  
		  if(stock == null) {
			  return null;
		  }
		  
		  else {
			  return stock;
		  }
		}

	@Override
	public String orderAllocation(Order order) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		//-----------------------------------------------1.OderAllocation Table-------------------------------------------------
		OrderAllocation newOrder = new OrderAllocation();
			
		newOrder.setOrderId(order.getId());
		newOrder.setDate(currentDateTime);
		newOrder.setTime(currentDateTime);
		newOrder.setDateTime(currentDateTime);
		    
		if(order.isOctane92()) {
			  newOrder.setAllocatedOcatane92(order.getQuantityOctane92());
			  newOrder.setStatusOcatane92("allocated");
		 }
		 else {
			  newOrder.setStatusOcatane92("none");
		 }
		   
		   
		 if(order.isOctane95()) {
			  newOrder.setAllocatedOcatane92(order.getQuantityOctane95());
			  newOrder.setStatusOcatane92("allocated");
		 }
		 else {
			  newOrder.setStatusOcatane92("none");
		 }
		   
		   
		 if(order.isAutoDiesel()) {
			  newOrder.setAllocatedAutoDiesel(order.getQuantityAutoDiesel());
			  newOrder.setStatusAutoDiesel("allocated");
		 }
		 else {
			  newOrder.setStatusAutoDiesel("none");
		 }
		   
		   
		 if(order.isOctane92()) {
			  newOrder.setAllocatedSuperDiesel(order.getQuantityOctane92());
			  newOrder.setStatusSuperDiesel("allocated");
		 }
		 else {
			  newOrder.setStatusSuperDiesel("none");
		 }
		 
		 orderAllocationRepository.save(newOrder);
		
		 
		
		//------------------------------------------------2.Stock Table---------------------------------------------------------
		
		return null;
	
	}
	
		
	public void getLastOrderAllocationRecord()
	{
		
	}
	

	
	@Override
	public List<Stock> findAllStockDesc() {
		
		return stockAllocationRepository.findAllByOrderByDateTimeDesc();
	}

	@Override
	public OrderAllocation findAllocationRecordByOrderID(Order order) {
		String orderID = order.getId();
		return orderAllocationRepository.findByOrderId(orderID);
	}




	
}
