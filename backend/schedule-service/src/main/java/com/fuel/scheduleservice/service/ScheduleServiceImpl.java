package com.fuel.scheduleservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fuel.orderservice.model.Order;
import com.fuel.scheduleservice.model.Schedule;
import com.fuel.scheduleservice.repository.ScheduleRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService{
	
	@Autowired
	ScheduleRepository scheduleRepository;

	@Override
	public Order scheduleDate(Order order) {
		String orderId = order.getId();
		String stationName = order.getStation();
	
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime scheduledDate = getScheduledTime(today);
		//LocalDateTime scheduledDate = getScheduledDate(today);
					
		Schedule schedule = new Schedule();
		schedule.setOrderId(orderId);
		schedule.setStationName(stationName);
		schedule.setScheduledDate(scheduledDate);
		
		scheduleRepository.save(schedule);
		
		order.setScheduledTime(scheduledDate);
		return order;
		
	}
	
	
	//Add 4 days from current time
	public LocalDateTime getScheduledDate(LocalDateTime today) {
		return today.plusDays(4);
	}
	
	//Add 5 seconds from current time
	public LocalDateTime getScheduledTime(LocalDateTime today) {
		return today.plusSeconds(5);
	}
}
