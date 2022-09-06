package com.ceypetco.scheduleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ScheduleDateDTO {

    private String id;
    private LocalDateTime scheduledDate;
    private String stationId;
    private int quantity;

}