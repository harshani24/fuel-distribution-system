package com.fuel.scheduleservice.service;

import com.fuel.orderservice.model.Order;
import com.fuel.scheduleservice.model.Schedule;

public interface ScheduleService {

	Order scheduleDate(Order order);
}
