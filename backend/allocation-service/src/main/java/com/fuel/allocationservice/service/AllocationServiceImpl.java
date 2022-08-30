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
		int octane92Quantity = order.getQuantityOctane92();
		int octane95Quantity = order.getQuantityOctane95();
		int autoDieselQuantity = order.getQuantityAutoDiesel();
		int superDieselQuantity = order.getQuantitySuperDiesel();
		
		boolean isOctane92 = order.isOctane92();
		boolean isOctane95 = order.isOctane95();
		boolean isAutoDiesel = order.isAutoDiesel();
		boolean isSuperDiesel = order.isSuperDiesel();
		
		//-----------------------------------------------1.OderAllocation Table-------------------------------------------------
		OrderAllocation newOrder = new OrderAllocation();
			
		newOrder.setOrderId(order.getId());
		newOrder.setDate(currentDateTime);
		newOrder.setTime(currentDateTime);
		newOrder.setDateTime(currentDateTime);
		    
		if(isOctane92) {
			  newOrder.setAllocatedOcatane92(octane92Quantity);
			  newOrder.setStatusOcatane92("allocated");
		 }
		 else {
			  newOrder.setStatusOcatane92("none");
		 }
		   
		   
		 if(isOctane95) {
			  newOrder.setAllocatedOcatane92(octane95Quantity);
			  newOrder.setStatusOcatane92("allocated");
		 }
		 else {
			  newOrder.setStatusOcatane92("none");
		 }
		   
		   
		 if(isAutoDiesel) {
			  newOrder.setAllocatedAutoDiesel(autoDieselQuantity);
			  newOrder.setStatusAutoDiesel("allocated");
		 }
		 else {
			  newOrder.setStatusAutoDiesel("none");
		 }
		   
		   
		 if(isSuperDiesel) {
			  newOrder.setAllocatedSuperDiesel(superDieselQuantity);
			  newOrder.setStatusSuperDiesel("allocated");
		 }
		 else {
			  newOrder.setStatusSuperDiesel("none");
		 }
		 
		 orderAllocationRepository.save(newOrder);
		
		 
		
		//------------------------------------------------2.Stock Table---------------------------------------------------------
		OrderAllocation addedOrderRecord = addedOrderRecord(order.getId());
		Stock lastStockRecord = getLastStockRecord();
		Stock newStockRecord = lastStockRecord.clone();
		
		int lastOctane92Quantity = lastStockRecord.getAvailableOcatane92();
		int lastOctane95Quantity = lastStockRecord.getAvailableOcatane92();
		int lastAutoDieselQuantity = lastStockRecord.getAvailableOcatane92();
		int lastSuperDieselQuantity = lastStockRecord.getAvailableOcatane92();
		
		if(addedOrderRecord != null) {
			newStockRecord.setOrderId(addedOrderRecord.getOrderId());
			newStockRecord.setOrderAllocationId(addedOrderRecord.getId());
			
			newStockRecord.setDate(currentDateTime);
			newStockRecord.setTime(currentDateTime);
			newStockRecord.setDateTime(currentDateTime);
			
			if(isOctane92) {
				newStockRecord.setAvailableOcatane92(lastOctane92Quantity + octane92Quantity);
				newStockRecord.setStatusOcatane92("allocated");
			 }
			 else {
				 newStockRecord.setStatusOcatane92("none");
			 }
			   
			   
			 if(isOctane95) {
				 newStockRecord.setAvailableOcatane95(lastOctane95Quantity + octane95Quantity);
				 newStockRecord.setStatusOcatane92("allocated");
			 }
			 else {
				 newStockRecord.setStatusOcatane92("none");
			 }
			   
			   
			 if(isAutoDiesel) {
				 newStockRecord.setAvailableAutoDiesel(lastAutoDieselQuantity + autoDieselQuantity);
				 newStockRecord.setStatusAutoDiesel("allocated");
			 }
			 else {
				 newStockRecord.setStatusAutoDiesel("none");
			 }
			   
			   
			 if(isSuperDiesel) {
				 newStockRecord.setAvailableSuperDiesel(lastSuperDieselQuantity + superDieselQuantity);
				 newStockRecord.setStatusSuperDiesel("allocated");
			 }
			 else {
				 newStockRecord.setStatusSuperDiesel("none");
			 }
			 
			 stockAllocationRepository.save( newStockRecord);
			
		}
		return null;
	
	}
	
	public OrderAllocation addedOrderRecord(String id) {
		OrderAllocation record = orderAllocationRepository.findTopByOrderByDateTimeDesc();
		
		if(record.getOrderId().equals(id)) {
			return record;
		}
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
