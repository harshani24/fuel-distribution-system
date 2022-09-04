package com.fuel.scheduleservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fuel.scheduleservice.model.Schedule;

public interface ScheduleRepository extends MongoRepository<Schedule, String>{

}
