package com.ceypetco.scheduleservice.repository;

import com.ceypetco.scheduleservice.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {

}
