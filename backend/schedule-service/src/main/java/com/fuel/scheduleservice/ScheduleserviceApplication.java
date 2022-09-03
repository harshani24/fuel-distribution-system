package com.fuel.scheduleservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fuel.orderservice.OrderserviceApplication;

@SpringBootApplication
public class ScheduleserviceApplication {
	
	public static final Logger logger = LoggerFactory.getLogger(ScheduleserviceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ScheduleserviceApplication.class, args);
	}
	  
}
