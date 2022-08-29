package com.fuel.allocationservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.fuel.allocationservice.model.Stock;
import com.fuel.allocationservice.repository.AllocationRepository;
import com.fuel.orderservice.model.Order;

//import static org.springframework.data.mongodb.core.query.Criteria.*;
//import static org.springframework.data.mongodb.core.query.Query.*;

//import org.springframework.data.mongodb.core.query.Query;

@Service
public class AllocationServiceImpl implements AllocationService{
	
	@Autowired
	AllocationRepository allocationRepository;

	@Override
	public Stock addStock(int octane92, int octane95, int autoDiesel, int superDiesel) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		Stock stock = getLastStockRecord();
		
		//Very First One
		if(stock == null) {
			Stock initialStock = new Stock();
			
			initialStock.setOrderId("00000");
			initialStock.setDate(currentDateTime);
			initialStock.setTime(currentDateTime);
			initialStock.setDateTime(currentDateTime);
			
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
			newStock.setDate(currentDateTime);
			newStock.setTime(currentDateTime);
			newStock.setDateTime(currentDateTime);
			
			newStock.setAvailableOcatane92(stock.getAvailableOcatane92()+octane92);
			newStock.setAvailableOcatane95(stock.getAvailableOcatane95()+octane95);
			newStock.setAvailableAutoDiesel(stock.getAvailableAutoDiesel()+autoDiesel);
			newStock.setAvailableSuperDiesel(stock.getAvailableSuperDiesel()+superDiesel);
			
			allocationRepository.save(newStock);
			
			return newStock;
		}	

	}
	
	@Override
	public String orderAllocation(Order order) {
		String fuelType = order.getFuelType();
		int quantity = order.getQuantity();
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		Stock newOrderAllocation = new Stock();
		
		
		Stock stock = getLastStockRecord();
		
		System.out.println(fuelType +" " + stock);
		
		Boolean stockAvailableForRequest = getAvailabilityForRequestQuantity(fuelType, quantity , stock , newOrderAllocation);
		
	    System.out.println(stockAvailableForRequest);
	    
	    if(stockAvailableForRequest) {
	    	newOrderAllocation.setOrderId(order.getId());
	    	newOrderAllocation.setDate(currentDateTime);
	    	newOrderAllocation.setTime(currentDateTime);
	    	newOrderAllocation.setDateTime(currentDateTime);
	    	
	    	newOrderAllocation.setAvailableOcatane92(stock.getAvailableOcatane92());	 
	    	newOrderAllocation.setAvailableOcatane95(stock.getAvailableOcatane95());
	    	newOrderAllocation.setAvailableAutoDiesel(stock.getAvailableAutoDiesel());
	    	newOrderAllocation.setAvailableSuperDiesel(stock.getAvailableSuperDiesel());
	    	
	    	
	    	allocationRepository.save(newOrderAllocation);
	    	
	    	System.out.println(newOrderAllocation);
	    	
	    	return "Your ordered fuel quantity is successfully allocated";
	    }
	    
	    else {
	    	return "Sorry, your requested fuel quantity can't be allocated for now";
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
	
	public Stock getLastStockRecordForParticularOrderID(Order order) {
		Stock stock = allocationRepository.findByOrderId(order.getId());
		
		  if(stock == null) {
			  return null;
		  }
		  
		  else {
			  return stock;
		  }
	}
	
	public boolean getAvailabilityForRequestQuantity(String fuelType ,int quantity, Stock stock , Stock newOrderAllocation) {
		
		boolean status = false;
		
		switch(fuelType) {
		
		case "OCTANE92": 
			              if(stock.getAvailableOcatane92() >= quantity ) {
			            	  status = true;
			            	  newOrderAllocation.setAllocatedOcatane92(quantity);
			              }
			              
			              else {
			            	  status = false;
			              }
			              
		case "OCTANE95": 
				          if(stock.getAvailableOcatane95() >= quantity ) {
				        	  status = true;
				        	  newOrderAllocation.setAllocatedOcatane95(quantity);
				          }	
				          else {
				        	  status = false;
			              }
				          
		case "AUTO_DIESEL": 
				          if(stock.getAvailableAutoDiesel() >= quantity ) {
				        	  status = true;
				        	  newOrderAllocation.setAllocatedAutoDiesel(quantity);
				          }
				          else {
				        	  status = false;
			              }
				          
		case "SUPER_DIESEL": 
				          if(stock.getAvailableSuperDiesel() >= quantity ) {
				        	  status = true;
				        	  newOrderAllocation.setAllocatedSuperDiesel(quantity);
				          }
				          else {
				        	  status = false;
			              }
			
	   }
		return status;
	
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

	
	@Override
	public Stock findStockByOrderID(Order order) {
	
		return allocationRepository.findByOrderId(order.getId());
	}
}
