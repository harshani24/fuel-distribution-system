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
			initialStock.setAllocatedTime(currentDateTime.toLocalTime());
			initialStock.setAllocatedDate(currentDateTime.toLocalDate());
			initialStock.setAllocatedDateTime(currentDateTime);
			initialStock.setTime(currentDateTime);
			
			initialStock.setAvailableOctane92(octane92);
			initialStock.setAvailableOctane95(octane95);
			initialStock.setAvailableAutoDiesel(autoDiesel);
			initialStock.setAvailableSuperDiesel(superDiesel);
			
			initialStock.setStatusOctane92("stocked");
			initialStock.setStatusOctane95("stocked");
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

			newStock.setAllocatedTime(currentDateTime.toLocalTime());
			newStock.setAllocatedDate(currentDateTime.toLocalDate());
			newStock.setAllocatedDateTime(currentDateTime);
			newStock.setTime(currentDateTime);
			
			newStock.setAvailableOctane92(stock.getAvailableOctane92()+octane92);
			newStock.setAvailableOctane95(stock.getAvailableOctane95()+octane95);
			newStock.setAvailableAutoDiesel(stock.getAvailableAutoDiesel()+autoDiesel);
			newStock.setAvailableSuperDiesel(stock.getAvailableSuperDiesel()+superDiesel);
			
			newStock.setStatusOctane92("stocked");
			newStock.setStatusOctane95("stocked");
			newStock.setStatusAutoDiesel("stocked");
			newStock.setStatusSuperDiesel("stocked");
			
			stockAllocationRepository.save(newStock);
			
			return newStock;
		}	

	}
	


	
	//=========================================2.Order Allocation===============================================================
	@Override
	public Order orderAllocation(Order order) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		//Last Record Available Quantities
		Stock lastStockRecord = getLastStockRecord();
		
		boolean allAvailable = checkOrderForAvailability(order, lastStockRecord);
		
		if(allAvailable) {
			//------------save in stock and orderallocation tables------------------------------
			
			//Stock Last Quantities
			int lastAllocatedOctane92Quantity = lastStockRecord.getAllocatedOctane92();
			int lastAllocatedOctane95Quantity = lastStockRecord.getAllocatedOctane95();
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
			
			newOrder.setOrderId(order.getId());
			newOrder.setAllocatedTime(currentDateTime.toLocalTime());		
			newOrder.setAllocatedDate(currentDateTime.toLocalDate());
			newOrder.setAllocatedDateTime(currentDateTime);
			
			
			if(isOctane92) {
				  newOrder.setAllocatedOctane92(octane92Quantity);
				  newOrder.setStatusOctane92("allocated");
				  
				  newStockRecord.setAllocatedOctane92(lastAllocatedOctane92Quantity + octane92Quantity);
				  newStockRecord.setStatusOctane92("allocated");
			 }
			 else {
				  newOrder.setStatusOctane92("none");
				  newStockRecord.setStatusOctane92("none");
			 }
			   
			   
			 if(isOctane95) {
				  newOrder.setAllocatedOctane95(octane95Quantity);
				  newOrder.setStatusOctane95("allocated");
				  
				  newStockRecord.setAllocatedOctane95(lastAllocatedOctane95Quantity + octane95Quantity);
				  newStockRecord.setStatusOctane95("allocated");
			 }
			 else {
				  newOrder.setStatusOctane95("none");
				  newStockRecord.setStatusOctane95("none");
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
			//no need this, because we can just access using  newOrder.getId()
			
			newStockRecord.setOrderId(newOrder.getOrderId());
			newStockRecord.setOrderAllocationId(newOrder.getId());
			newStockRecord.setAllocatedTime(newOrder.getAllocatedTime());
			newStockRecord.setAllocatedDate(newOrder.getAllocatedDate());
			newStockRecord.setAllocatedDateTime(newOrder.getAllocatedDateTime());	
			newStockRecord.setTime(currentDateTime);
			
			stockAllocationRepository.save(newStockRecord);
			
			order.setAllocated(true);
		    order.setAllocatedTime(newOrder.getAllocatedDateTime());
			
			return order;
		}
		
		else {
			
			return order;
		}
			
	}
	

	public boolean checkOrderForAvailability(Order order, Stock lastStockRecord)
	{
		//Last Record Available Quantities
		int lastOctane92Quantity = lastStockRecord.getAvailableOctane92();
		int lastOctane95Quantity = lastStockRecord.getAvailableOctane92();
		int lastAutoDieselQuantity = lastStockRecord.getAvailableOctane92();
		int lastSuperDieselQuantity = lastStockRecord.getAvailableOctane92();
		
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
	
	@Override
	public void updateStock(Order order) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		//------------------1.Order Allocation Table Update
		//get order record from Order Allocation Table
		OrderAllocation orderRecord = findAllocationRecordByOrderID(order);
		
		//update the order allocation record
		OrderAllocation updatedOrderRecord = updateAlloctatedQuanOrderAlocation(orderRecord , currentDateTime);
		System.out.println("Updated Order Record "+updatedOrderRecord);
		
		
		//------------------2.Stock Table Update
		//get the last stock record
		Stock lastStockRecord = getLastStockRecord();
		Stock stockRecord = lastStockRecord.clone();
		
		String orderAllocationId = updatedOrderRecord.getId();
		
		//add new record by updating dispatched quantity
		Stock updatedStockRecord = updateAlloctatedQuanStock(orderAllocationId,order,stockRecord ,currentDateTime);
		System.out.println("Updated Stock Record "+updatedStockRecord);
		
	}



	//----------------------------------------------------other-------------------------------------------------------------------
	public Stock getLastStockRecord() {
		  Stock stock = stockAllocationRepository.findFirstByOrderByTimeDesc();
	
		  if(stock == null) {
			  return null;
		  }
		  
		  else {
			  return stock;
		  }
	}
	
	
	@Override
	public List<Stock> findAllStockDesc() {
		
		return stockAllocationRepository.findAllByOrderByTimeDesc();
		//return stockAllocationRepository.findTop20ByOrderByTimeDesc();
	}

	
	public OrderAllocation findAllocationRecordByOrderID(Order order) {
		String orderID = order.getId();
		return orderAllocationRepository.findByOrderId(orderID);
	}
	
	public OrderAllocation updateAlloctatedQuanOrderAlocation(OrderAllocation orderRecord , LocalDateTime currentDateTime)
	{
		if(orderRecord.getAllocatedOctane92() > 0) {
			orderRecord.setDispatchedOctane92(orderRecord.getDispatchedOctane92() + orderRecord.getAllocatedOctane92());
			orderRecord.setAllocatedOctane92(0);
			orderRecord.setStatusOctane92("dispatched");
		}
		if(orderRecord.getAllocatedOctane95() > 0) {
			orderRecord.setDispatchedOctane95(orderRecord.getDispatchedOctane95() + orderRecord.getAllocatedOctane95());
			orderRecord.setAllocatedOctane95(0);	
			orderRecord.setStatusOctane95("dispatched");
		}
		if(orderRecord.getAllocatedAutoDiesel() > 0) {
			orderRecord.setDispatchedAutoDiesel(orderRecord.getDispatchedAutoDiesel() + orderRecord.getAllocatedAutoDiesel());
			orderRecord.setAllocatedAutoDiesel(0);
			orderRecord.setStatusAutoDiesel("dispatched");
		}
		if(orderRecord.getAllocatedSuperDiesel() > 0) {
			orderRecord.setDispatchedSuperDiesel(orderRecord.getDispatchedSuperDiesel() + orderRecord.getAllocatedSuperDiesel());
			orderRecord.setAllocatedSuperDiesel(0);
			orderRecord.setStatusSuperDiesel("dispatched");
		}
		
		orderRecord.setDispatchedDate(currentDateTime.toLocalDate());
		orderRecord.setDispatchedTime(currentDateTime.toLocalTime());
		orderRecord.setDispatchedDateTime(currentDateTime);
		
		orderAllocationRepository.save(orderRecord);
		
		return orderRecord;
	}
	
	private Stock updateAlloctatedQuanStock(String  orderAllocationId,Order order, Stock lastStockRecord, LocalDateTime currentDateTime) {
		
		//Stock Last Available Quantities
		int lastAvailableOctane92Quantity = lastStockRecord.getAvailableOctane92();
		int lastAvailableOctane95Quantity = lastStockRecord.getAvailableOctane95();
		int lastAvailableAutoDieselQuantity = lastStockRecord.getAvailableAutoDiesel();
		int lastAvailableSuperDieselQuantity = lastStockRecord.getAvailableSuperDiesel();
		
		//Stock Last  Allocated Quantities
		int lastAllocatedOctane92Quantity = lastStockRecord.getAllocatedOctane92();
		int lastAllocatedOctane95Quantity = lastStockRecord.getAllocatedOctane95();
		int lastAllocatedAutoDieselQuantity = lastStockRecord.getAllocatedAutoDiesel();
		int lastAllocatedSuperDieselQuantity = lastStockRecord.getAllocatedSuperDiesel();
		
		//Stock Last Dispatched Quantities
		int lastDispatchedOctane92Quantity = lastStockRecord.getDispatchedOctane92();
		int lastDispatchedOctane95Quantity = lastStockRecord.getDispatchedOctane95();
		int lastDispatchedAutoDieselQuantity = lastStockRecord.getDispatchedAutoDiesel();
		int lastDispatchedSuperDieselQuantity = lastStockRecord.getDispatchedSuperDiesel();
		
		//All Fuel Quantities which are gonna update
		int updateOctane92Quantity = order.getQuantityOctane92();
		int updateOctane95Quantity = order.getQuantityOctane95();
		int updateAutoDieselQuantity = order.getQuantityAutoDiesel();
		int updateSuperDieselQuantity = order.getQuantitySuperDiesel();
		
		
		if(order.isOctane92()) {
			lastStockRecord.setAvailableOctane92(lastAvailableOctane92Quantity - updateOctane92Quantity );
			lastStockRecord.setAllocatedOctane92(lastAllocatedOctane92Quantity - updateOctane92Quantity);
			lastStockRecord.setDispatchedOctane92(lastDispatchedOctane92Quantity + updateOctane92Quantity);
			lastStockRecord.setStatusOctane92("dispatched");
			
		}
		if(order.isOctane95()) {
			lastStockRecord.setAvailableOctane95(lastAvailableOctane95Quantity - updateOctane95Quantity );
			lastStockRecord.setAllocatedOctane95(lastAllocatedOctane95Quantity - updateOctane95Quantity );
			lastStockRecord.setDispatchedOctane95(lastDispatchedOctane95Quantity + updateOctane95Quantity );
			lastStockRecord.setStatusOctane95("dispatched");
		}
		if(order.isAutoDiesel()) {
			lastStockRecord.setAvailableAutoDiesel(lastAvailableAutoDieselQuantity - updateAutoDieselQuantity );
			lastStockRecord.setAllocatedAutoDiesel(lastAllocatedAutoDieselQuantity - updateAutoDieselQuantity );
			lastStockRecord.setDispatchedAutoDiesel(lastDispatchedAutoDieselQuantity + updateAutoDieselQuantity );
			lastStockRecord.setStatusAutoDiesel("dispatched");
		}
		if(order.isSuperDiesel()) {
			lastStockRecord.setAvailableSuperDiesel(lastAvailableSuperDieselQuantity - updateSuperDieselQuantity);
			lastStockRecord.setAllocatedSuperDiesel(lastAllocatedSuperDieselQuantity - updateSuperDieselQuantity);
			lastStockRecord.setDispatchedSuperDiesel(lastDispatchedSuperDieselQuantity + updateSuperDieselQuantity);
			lastStockRecord.setStatusSuperDiesel("dispatched");
		}
		
		lastStockRecord.setOrderId(order.getId());
		lastStockRecord.setOrderAllocationId(orderAllocationId);
		
		lastStockRecord.setDispatchedDate(currentDateTime.toLocalDate());
		lastStockRecord.setDispatchedTime(currentDateTime.toLocalTime());
		lastStockRecord.setDispatchedDateTime(currentDateTime);
		lastStockRecord.setTime(currentDateTime);
		
		return lastStockRecord;
	}




	@Override
	public List<OrderAllocation> findAllOrderAllocation() {
		
		return orderAllocationRepository.findAll();
	}

	
	
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
