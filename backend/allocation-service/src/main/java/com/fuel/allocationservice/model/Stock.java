package com.fuel.allocationservice.model;

import java.time.LocalDateTime;

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
	
	@Indexed(unique = true)
	private String orderId;	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime date;
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalDateTime time;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateTime;
	
	private int availableOcatane92;
	private int allocatedOcatane92;
	private int dispatchedOcatane92;
	
	private int availableOcatane95;
	private int allocatedOcatane95;
	private int dispatchedOcatane95;
	
	private int availableAutoDiesel;
	private int allocatedAutoDiesel;
	private int dispatchedAutoDiesel;
	
	private int availableSuperDiesel;
	private int allocatedSuperDiesel;
	private int dispatchedSuperDiesel;

	public Stock clone() {
		Stock stock = new Stock();
		
		stock.setOrderId(orderId);
		
		stock.setAvailableOcatane92(availableOcatane92);
		stock.setAllocatedOcatane92(allocatedOcatane92);
		stock.setDispatchedOcatane92(dispatchedOcatane92);
		
		stock.setAvailableOcatane95(availableOcatane95);
		stock.setAllocatedOcatane95(allocatedOcatane95);
		stock.setDispatchedOcatane95(dispatchedOcatane95);
		
		stock.setAvailableAutoDiesel(availableAutoDiesel);
		stock.setAllocatedAutoDiesel(allocatedAutoDiesel);
		stock.setDispatchedAutoDiesel(dispatchedAutoDiesel);
		
		stock.setAvailableSuperDiesel(availableSuperDiesel);
		stock.setAllocatedSuperDiesel(allocatedSuperDiesel);
		stock.setDispatchedSuperDiesel(dispatchedSuperDiesel);
		
		return stock;
	}
}
