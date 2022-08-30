package com.fuel.orderservice.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document("Order")
public class Order {
	
	@Id
	private String id;
    
	@Indexed(unique = true)
	private String passport;
	private String station;
//	private String fuelType;
//	private int quantity;
	
	private boolean octane92;
	private int quantityOctane92;
	
	private boolean octane95;
	private int quantityOctane95;
	
	private boolean autoDiesel;
	private int quantityAutoDiesel;
	
	private boolean superDiesel;
	private int quantitySuperDiesel;
	
	private boolean allocated;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime allocatedTime;
	
	private boolean sheduled;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime scheduledTime;
	
	private boolean dispatched;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dispatchedTime;
	
	private boolean completed;
	//@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime completedTime;
	
	private String status;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime statusTime;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime statusDate;

}
