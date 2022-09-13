package com.fuel.orderservice.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ScheduleDTO {

	private String orderId;
	private String station;
	private String passport;
	
	private boolean octane92;
	private int quantityOctane92;
	
	private boolean octane95;
	private int quantityOctane95;
	
	private boolean autoDiesel;
	private int quantityAutoDiesel;
	
	private boolean superDiesel;
	private int quantitySuperDiesel;
	
	
	private LocalDateTime scheduledDate;
	private LocalDateTime dispatchedDate;
	
}
