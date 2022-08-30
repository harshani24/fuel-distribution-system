package com.fuel.allocationservice.model;

import java.time.LocalDateTime;

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
	private LocalDateTime date;
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalDateTime time;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateTime;
	
	private int allocatedOcatane92;
	private int dispatchedOcatane92;
	private String statusOcatane92;
	
	private int allocatedOcatane95;
	private int dispatchedOcatane95;
	private String statusOcatane95;
	
	private int allocatedAutoDiesel;
	private int dispatchedAutoDiesel;
	private String statusAutoDiesel;
	
	private int allocatedSuperDiesel;
	private int dispatchedSuperDiesel;
	private String statusSuperDiesel;
	
	public OrderAllocation clone() {
		
	   OrderAllocation order = new OrderAllocation();
	   
	   order.setOrderId(orderId);
	   
	   order.setAllocatedOcatane92(allocatedOcatane92);
	   order.setAllocatedOcatane95(allocatedOcatane95);
	   order.setAllocatedAutoDiesel(allocatedAutoDiesel);
	   order.setAllocatedSuperDiesel(allocatedSuperDiesel);
	   
	   order.setDispatchedOcatane92(dispatchedOcatane92);
	   order.setDispatchedOcatane95(dispatchedOcatane95);
	   order.setDispatchedAutoDiesel(dispatchedAutoDiesel);
	   order.setDispatchedSuperDiesel(dispatchedSuperDiesel);
	   
	   return order;
	}
	
}
