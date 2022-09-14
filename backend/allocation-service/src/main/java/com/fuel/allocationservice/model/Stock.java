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
@Document("Stock")
public class Stock{
	
	@Id
	private String id;
	
	private String orderId;	
	private String orderAllocationId;	
	
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
	
	private int availableOctane92;
	private int allocatedOctane92;
	private int dispatchedOctane92;
	private String statusOctane92;
	
	private int availableOctane95;
	private int allocatedOctane95;
	private int dispatchedOctane95;
	private String statusOctane95;
	
	private int availableAutoDiesel;
	private int allocatedAutoDiesel;
	private int dispatchedAutoDiesel;
	private String statusAutoDiesel;
	
	private int availableSuperDiesel;
	private int allocatedSuperDiesel;
	private int dispatchedSuperDiesel;
	private String statusSuperDiesel;
	
	

	public Stock clone() {
		Stock stock = new Stock();
		
		stock.setAvailableOctane92(availableOctane92);
		stock.setAllocatedOctane92(allocatedOctane92);
		stock.setDispatchedOctane92(dispatchedOctane92);
		stock.setStatusOctane92(statusOctane92);
		
		stock.setAvailableOctane95(availableOctane95);
		stock.setAllocatedOctane95(allocatedOctane95);
		stock.setDispatchedOctane95(dispatchedOctane95);
		stock.setStatusOctane95(statusOctane95);
		
		stock.setAvailableAutoDiesel(availableAutoDiesel);
		stock.setAllocatedAutoDiesel(allocatedAutoDiesel);
		stock.setDispatchedAutoDiesel(dispatchedAutoDiesel);
		stock.setStatusAutoDiesel(statusAutoDiesel);
		
		stock.setAvailableSuperDiesel(availableSuperDiesel);
		stock.setAllocatedSuperDiesel(allocatedSuperDiesel);
		stock.setDispatchedSuperDiesel(dispatchedSuperDiesel);
		stock.setStatusSuperDiesel(statusSuperDiesel);
		
		return stock;
	}
}
