package com.fuel.allocationservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document("Order_Allocation")
public class OrderAllocation{
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	private String orderId;	
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate allocatedDate;
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime allocatedTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime allocatedDateTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dispatchedDate;
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime dispatchedTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dispatchedDateTime;
	
	private int allocatedOctane92;
	private int dispatchedOctane92;
	private String statusOctane92;
	
	private int allocatedOctane95;
	private int dispatchedOctane95;
	private String statusOctane95;
	
	private int allocatedAutoDiesel;
	private int dispatchedAutoDiesel;
	private String statusAutoDiesel;
	
	private int allocatedSuperDiesel;
	private int dispatchedSuperDiesel;
	private String statusSuperDiesel;
	
	public OrderAllocation clone() {
		
	   OrderAllocation order = new OrderAllocation();
	   
	   order.setOrderId(orderId);
	   
	   order.setAllocatedOctane92(allocatedOctane92);
	   order.setAllocatedOctane95(allocatedOctane95);
	   order.setAllocatedAutoDiesel(allocatedAutoDiesel);
	   order.setAllocatedSuperDiesel(allocatedSuperDiesel);
	   
	   order.setDispatchedOctane92(dispatchedOctane92);
	   order.setDispatchedOctane95(dispatchedOctane95);
	   order.setDispatchedAutoDiesel(dispatchedAutoDiesel);
	   order.setDispatchedSuperDiesel(dispatchedSuperDiesel);
	   
	   return order;
	}
	
}
