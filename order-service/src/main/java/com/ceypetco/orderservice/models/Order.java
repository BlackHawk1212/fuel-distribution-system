package com.ceypetco.orderservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Document("order")
public class Order {
    @Id
    private String id; // reference id

    private String stationId;
    private FuelType fuelType;
    private Quantity quantity;
    private boolean reserved; // station created the order
    private LocalDateTime reservedTime;
    private boolean qtyAllocated; // quantity got allocated
    private LocalDateTime allocatedTime;
    private boolean scheduled; // delivery date scheduled
    private LocalDateTime scheduledTime;
    private boolean dispatched; // dispatched on the said date
    private LocalDateTime dispatchedTime;
    private boolean delivered; // delivered to the station
    private LocalDateTime deliveredTime;

}
