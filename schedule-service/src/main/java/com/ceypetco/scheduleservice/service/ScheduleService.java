package com.ceypetco.scheduleservice.service;

import com.ceypetco.scheduleservice.model.Schedule;
import com.ceypetco.scheduleservice.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService{

    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule submitScheduleRecord(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }
}
