package com.fuel.orderservice.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("Order")
public class Order {
	
	@Id
	private String id;
    
	@Indexed(unique = true)
	private String passport;
	private String station;
	private String fuelType;
	private int quantity;
	
	private boolean allocated;
	private LocalDateTime allocatedTime;
	private boolean sheduled;
	private LocalDateTime scheduledTime;
	private boolean dispatched;
	private LocalDateTime dispatchedTime;
	private boolean completed;
	private LocalDateTime completedTime;
	
	private String status;
	private LocalDateTime statusTime;

}
