package com.fuel.orderservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuel.orderservice.dto.ScheduleDTO;
import com.fuel.orderservice.model.Order;
import com.fuel.orderservice.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository orderRepository;
	
	
	@Override
	//create order id and save in db
	public Order addOrder(Order order) {
		
	    String id = generateID(order); 
	    LocalDateTime currentTime = LocalDateTime.now();
		order.setId(id);
		
		order.setOrdered(true);
		order.setOrderedTime(currentTime);
		
		order.setStatus("allocation needed");
		order.setStatusDate(currentTime.toLocalDate());
		order.setStatusTime(currentTime.toLocalTime());
		order.setStatusDateTime(currentTime);
		
		orderRepository.save(order);
		return order;
	}
	
    //generate id for the order
	private String generateID(Order order) {
		String id = order.getPassport() + UUID.randomUUID().toString().substring(0, 5);
		
		return id;
	}

	@Override
	//get one order
	public Order getOrder(String id) {
		Optional<Order> order = orderRepository.findById(id);
	
		if(order.isPresent()) {
		 return order.get();
		}
		return null;
		
	}
	
	@Override
	//get order status
	public String getOrderStatus(String id) {
	Optional<Order> order = orderRepository.findById(id);
	
		if(order.isPresent()) {
		 return order.get().getStatus();
		}
		return "Order id is incorrect";
	}
	
	
	@Override
	public List<Order> viewAllOrders(){
		List<Order> orders = orderRepository.findAll();
		return orders;
		
	}
	
	@Override    
	public String receivedConfirm(String id) {
		Optional<Order> order = orderRepository.findById(id);
		
		if(order.isPresent()) {
			return completeOrder(order.get());
		}
		else {
			return "There is something went wrong with order id";
		}
			
	}
	
	public String completeOrder(Order order) {
		LocalDateTime time = LocalDateTime.now() ;
	
		order.setCompleted(true);
		order.setCompletedTime(time);
		
		order.setStatus("completed");
		order.setStatusTime(time.toLocalTime());
		order.setStatusDate(time.toLocalDate());
		order.setStatusDateTime(time);
		
		orderRepository.save(order);
		
		return "Order Completed Successfully";
	}

	@Override
	public Order changeAllocationStatus(Order currentOrder) {
		
		Optional<Order> order = orderRepository.findById(currentOrder.getId());
		
		if(order.isPresent()) {
			return updateStatusAllocation(currentOrder);
		}
		
		return null;	
	}

	private Order updateStatusAllocation(Order order) {
		
		if(order.isAllocated()) {			
			order.setStatus("allocated");
			
			LocalDateTime allocatedTime  = order.getAllocatedTime();	
			
			order.setStatusTime(allocatedTime.toLocalTime());
			order.setStatusDate(allocatedTime.toLocalDate());
			order.setStatusDateTime(allocatedTime);		
			
			orderRepository.save(order);
		}
		
		else{
			LocalDateTime currentDateTime = LocalDateTime.now();
			
			order.setRejected(true);
			order.setScheduledTime(currentDateTime);
			
			order.setStatus("rejected");
			order.setStatusTime(currentDateTime.toLocalTime());			
			order.setStatusDate(currentDateTime.toLocalDate());
			order.setStatusDateTime(currentDateTime);
			
			orderRepository.save(order);
		}

		return order;
	}

	@Override
	public ScheduleDTO addScheduledDate(Order order) {
		
		LocalDateTime scheduledDate = order.getScheduledTime();
		
		order.setScheduled(true);
		
		order.setStatus("scheduled");
		order.setStatusDate(scheduledDate.toLocalDate());
		order.setStatusTime(scheduledDate.toLocalTime());
		order.setStatusDateTime(scheduledDate);
		
		Order scheduledOrder = orderRepository.save(order);
		return getScheduledOrderForDispatch(scheduledOrder);
	}
	
	public ScheduleDTO getScheduledOrderForDispatch(Order o) {
		
		ScheduleDTO scheduledDTO = new ScheduleDTO();
		
		scheduledDTO.setOrderId(o.getId());
		scheduledDTO.setPassport(o.getPassport());
		scheduledDTO.setStation(o.getStation());
		
		scheduledDTO.setOctane92(o.isOctane92());
		scheduledDTO.setQuantityOctane92(o.getQuantityOctane92());
		scheduledDTO.setOctane95(o.isOctane95());
		scheduledDTO.setQuantityOctane95(o.getQuantityOctane95());
		scheduledDTO.setAutoDiesel(o.isAutoDiesel());
		scheduledDTO.setQuantityAutoDiesel(o.getQuantityAutoDiesel());
		scheduledDTO.setSuperDiesel(o.isSuperDiesel());
		scheduledDTO.setQuantitySuperDiesel(o.getQuantitySuperDiesel());
		
		scheduledDTO.setScheduledDate(o.getScheduledTime());
		
		return scheduledDTO;
		
	}
	
	@Override
	public void changeDispatchStatus(String orderId) {
		Order order = orderRepository.findOrderById(orderId);
		LocalDateTime currentDateTime = LocalDateTime.now() ;
		
		order.setDispatched(true);
		order.setDispatchedTime(currentDateTime);
		
		order.setStatus("dispatched");
		order.setStatusDate(currentDateTime.toLocalDate());
		order.setStatusTime(currentDateTime.toLocalTime());
		order.setStatusDateTime(currentDateTime);
		
		orderRepository.save(order);
		
		System.out.println("After changing dispatch status " + order);
	}

	@Override
	public List<Order> viewAllMyOrders(String passport) {
		List<Order> orders = orderRepository.findAllByPassport(passport);	
		return orders;
	}

}
