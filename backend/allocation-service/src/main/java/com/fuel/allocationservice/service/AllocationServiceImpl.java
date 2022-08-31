package com.fuel.allocationservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuel.allocationservice.model.OrderAllocation;
import com.fuel.allocationservice.model.Stock;
import com.fuel.allocationservice.repository.OrderAllocationRepository;
import com.fuel.allocationservice.repository.StockAllocationRepository;
import com.fuel.orderservice.model.Order;
import com.fuel.orderservice.repository.OrderRepository;


@Service
public class AllocationServiceImpl implements AllocationService{
	
	@Autowired
	StockAllocationRepository stockAllocationRepository;
	
	@Autowired
	OrderAllocationRepository orderAllocationRepository;

	//=========================================1.Add Stock to distributor=======================================================
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

	
	//=========================================2.Order Allocation===============================================================
	@Override
	public boolean orderAllocation(Order order) {
		
		//Last Record Available Quantities
		Stock lastStockRecord = getLastStockRecord();
		
		boolean allAvailable = checkOrderForAvailability(order, lastStockRecord);
		
		if(allAvailable) {
			//------------save in stock and orderallocation tables------------------------------
			
			//Stock Last Quantities
			int lastAllocatedOctane92Quantity = lastStockRecord.getAllocatedOcatane92();
			int lastAllocatedOctane95Quantity = lastStockRecord.getAllocatedOcatane95();
			int lastAllocatedAutoDieselQuantity = lastStockRecord.getAllocatedAutoDiesel();
			int lastAllocatedSuperDieselQuantity = lastStockRecord.getAllocatedSuperDiesel();
			
			//New order Quantities
			int octane92Quantity = order.getQuantityOctane92();
			int octane95Quantity = order.getQuantityOctane95();
			int autoDieselQuantity = order.getQuantityAutoDiesel();
			int superDieselQuantity = order.getQuantitySuperDiesel();
			
			boolean isOctane92 = order.isOctane92();
			boolean isOctane95 = order.isOctane95();
			boolean isAutoDiesel = order.isAutoDiesel();
			boolean isSuperDiesel = order.isSuperDiesel();
			
			OrderAllocation newOrder = new OrderAllocation();
			Stock newStockRecord = lastStockRecord.clone();
			
			LocalDateTime currentDateTime = LocalDateTime.now();
			
			newOrder.setOrderId(order.getId());
			newOrder.setDate(currentDateTime);
			newOrder.setTime(currentDateTime);
			newOrder.setDateTime(currentDateTime);
			
			
			if(isOctane92) {
				  newOrder.setAllocatedOcatane92(octane92Quantity);
				  newOrder.setStatusOcatane92("allocated");
				  
				  newStockRecord.setAllocatedOcatane92(lastAllocatedOctane92Quantity + octane92Quantity);
				  newStockRecord.setStatusOcatane92("allocated");
			 }
			 else {
				  newOrder.setStatusOcatane92("none");
				  newStockRecord.setStatusOcatane92("none");
			 }
			   
			   
			 if(isOctane95) {
				  newOrder.setAllocatedOcatane95(octane95Quantity);
				  newOrder.setStatusOcatane95("allocated");
				  
				  newStockRecord.setAllocatedOcatane95(lastAllocatedOctane95Quantity + octane95Quantity);
				  newStockRecord.setStatusOcatane95("allocated");
			 }
			 else {
				  newOrder.setStatusOcatane95("none");
				  newStockRecord.setStatusOcatane95("none");
			 }
			   
			   
			 if(isAutoDiesel) {
				  newOrder.setAllocatedAutoDiesel(autoDieselQuantity);
				  newOrder.setStatusAutoDiesel("allocated");
				  
				  newStockRecord.setAllocatedAutoDiesel(lastAllocatedAutoDieselQuantity + autoDieselQuantity);
				  newStockRecord.setStatusAutoDiesel("allocated");
			 }
			 else {
				  newOrder.setStatusAutoDiesel("none");
				  newStockRecord.setStatusAutoDiesel("none");
			 }
			   
			   
			 if(isSuperDiesel) {
				  newOrder.setAllocatedSuperDiesel(superDieselQuantity);
				  newOrder.setStatusSuperDiesel("allocated");
				  
				  newStockRecord.setAllocatedSuperDiesel(lastAllocatedSuperDieselQuantity + superDieselQuantity);
				  newStockRecord.setStatusSuperDiesel("allocated");
			 }
			 else {
				  newOrder.setStatusSuperDiesel("none");
				  newStockRecord.setStatusSuperDiesel("none");
			 }
			 

			 orderAllocationRepository.save(newOrder);
			 
			 
			//last added orderRecord from OrderAllocation table
			//OrderAllocation addedOrderRecord = addedOrderRecordInOrderAllocation(order.getId());
			//no need this, bcz we can just access using  newOrder.getId()
			
			newStockRecord.setOrderId(newOrder.getOrderId());
			newStockRecord.setOrderAllocationId(newOrder.getId());
			newStockRecord.setDate(currentDateTime);
			newStockRecord.setTime(currentDateTime);
			newStockRecord.setDateTime(currentDateTime);
			
			
			stockAllocationRepository.save( newStockRecord);
			
			return true;
		}
		
		else {
			order.setStatus("rejected");
			
			return false;
		}
			
	}
	

	public boolean checkOrderForAvailability(Order order, Stock lastStockRecord)
	{
		//Last Record Available Quantities
		int lastOctane92Quantity = lastStockRecord.getAvailableOcatane92();
		int lastOctane95Quantity = lastStockRecord.getAvailableOcatane92();
		int lastAutoDieselQuantity = lastStockRecord.getAvailableOcatane92();
		int lastSuperDieselQuantity = lastStockRecord.getAvailableOcatane92();
		
		//New order Quantities
		int octane92Quantity = order.getQuantityOctane92();
		int octane95Quantity = order.getQuantityOctane95();
		int autoDieselQuantity = order.getQuantityAutoDiesel();
		int superDieselQuantity = order.getQuantitySuperDiesel();
		
		boolean allAvailable = false;

		
		if( (lastOctane92Quantity >= octane92Quantity) && (lastOctane95Quantity >= octane95Quantity) &&(lastAutoDieselQuantity >= autoDieselQuantity) &&(lastSuperDieselQuantity >= superDieselQuantity) ) {
			allAvailable = true;
		}
		
		return allAvailable;
		
		
	}

	
	
	//========================================3.Dispatch Orders=================================================================
	
	
	//----------------------------------------------------other-------------------------------------------------------------------
	@Override
	public List<Stock> findAllStockDesc() {
		
		return stockAllocationRepository.findAllByOrderByDateTimeDesc();
	}

//	@Override
//	public OrderAllocation findAllocationRecordByOrderID(Order order) {
//		String orderID = order.getId();
//		return orderAllocationRepository.findByOrderId(orderID);
//	}
	
	//no need this actually
//	public OrderAllocation addedOrderRecordInOrderAllocation(String id) {
//		OrderAllocation record = orderAllocationRepository.findTopByOrderByDateTimeDesc();
//		
//		if(record.getOrderId().equals(id)) {
//			return record;
//		}
//		return null;
//		
//	}
}
