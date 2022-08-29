package com.ceypetco.scheduleservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Document("schedule")
public class Schedule {

    @Id
    private String timeStamp;

    private String orderId;
    private LocalDateTime scheduledDate;

}
