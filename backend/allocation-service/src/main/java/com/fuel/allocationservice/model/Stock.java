package com.fuel.allocationservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document("Stock")
public class Stock {
	
	@Id
	private String id;
	
	private String orderId;	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime time;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateTime;
	
	private int availableOcatane92;
	private int allocatedOcatane92;
	
	private int availableOcatane95;
	private int allocatedOcatane95;
	
	private int availableAutoDiesel;
	private int allocatedAutoDiesel;
	
	private int availableSuperDiesel;
	private int allocatedSuperDiesel;

}
