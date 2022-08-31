package com.fuel.allocationservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.fuel.allocationservice.controller.AllocationController;

@SpringBootApplication
public class AllocationserviceApplication {

	@Autowired
	AllocationController allocationController;
	
	public static final Logger logger = LoggerFactory.getLogger(AllocationserviceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(AllocationserviceApplication.class, args);
	}
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void EventListenerExecute() {
		System.out.println("Application Ready Event is successfully Started");
		
		allocationController.addStock(20000,20000,20000,20000);
	}

}
