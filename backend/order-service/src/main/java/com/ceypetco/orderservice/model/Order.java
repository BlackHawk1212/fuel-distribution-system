package com.ceypetco.orderservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

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

    public Order() {

    }

    public Order(String id, String stationId,
                 FuelType fuelType, Quantity quantity,
                 boolean reserved, LocalDateTime reservedTime,
                 boolean qtyAllocated, LocalDateTime allocatedTime,
                 boolean scheduled, LocalDateTime scheduledTime,
                 boolean dispatched, LocalDateTime dispatchedTime,
                 boolean delivered, LocalDateTime deliveredTime) {
        this.id = id;
        this.stationId = stationId;
        this.fuelType = fuelType;
        this.quantity = quantity;
        this.reserved = false;
        this.reservedTime = reservedTime;
        this.qtyAllocated = false;
        this.allocatedTime = allocatedTime;
        this.scheduled = false;
        this.scheduledTime = scheduledTime;
        this.dispatched = false;
        this.dispatchedTime = dispatchedTime;
        this.delivered = false;
        this.deliveredTime = deliveredTime;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("stationId")
    public String getStationId() {
        return stationId;
    }

    @JsonProperty("stationId")
    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    @JsonProperty("fuelType")
    public FuelType getFuelType() {
        return fuelType;
    }

    @JsonProperty("fuelType")
    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    @JsonProperty("quantity")
    public Quantity getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("reserved")
    public boolean isReserved() {
        return reserved;
    }

    @JsonProperty("reserved")
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonProperty("reservedTime")
    public LocalDateTime getReservedTime() {
        return reservedTime;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty("reservedTime")
    public void setReservedTime(LocalDateTime reservedTime) {
        this.reservedTime = reservedTime;
    }

    @JsonProperty("qtyAllocated")
    public boolean isQtyAllocated() {
        return qtyAllocated;
    }

    @JsonProperty("qtyAllocated")
    public void setQtyAllocated(boolean qtyAllocated) {
        this.qtyAllocated = qtyAllocated;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonProperty("allocatedTime")
    public LocalDateTime getAllocatedTime() {
        return allocatedTime;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty("allocatedTime")
    public void setAllocatedTime(LocalDateTime allocatedTime) {
        this.allocatedTime = allocatedTime;
    }

    @JsonProperty("scheduled")
    public boolean isScheduled() {
        return scheduled;
    }

    @JsonProperty("scheduled")
    public void setScheduled(boolean scheduled) {
        this.scheduled = scheduled;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonProperty("scheduledTime")
    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty("scheduledTime")
    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    @JsonProperty("dispatched")
    public boolean isDispatched() {
        return dispatched;
    }

    @JsonProperty("dispatched")
    public void setDispatched(boolean dispatched) {
        this.dispatched = dispatched;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonProperty("dispatchedTime")
    public LocalDateTime getDispatchedTime() {
        return dispatchedTime;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty("dispatchedTime")
    public void setDispatchedTime(LocalDateTime dispatchedTime) {
        this.dispatchedTime = dispatchedTime;
    }

    @JsonProperty("delivered")
    public boolean isDelivered() {
        return delivered;
    }

    @JsonProperty("delivered")
    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonProperty("deliveredTime")
    public LocalDateTime getDeliveredTime() {
        return deliveredTime;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty("deliveredTime")
    public void setDeliveredTime(LocalDateTime deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    @Override
    public String toString() {
        return "Order {id='" + id + '\'' + ", stationId='" + stationId + '\'' + ", fuelType=" + fuelType + ", quantity=" + quantity +
                ", reserved=" + reserved + ", reservedTime=" + reservedTime + ", qtyAllocated=" + qtyAllocated + ", allocatedTime=" + allocatedTime +
                ", scheduled=" + scheduled + ", scheduledTime=" + scheduledTime + ", dispatched=" + dispatched + ", dispatchedTime=" + dispatchedTime +
                ", delivered=" + delivered + ", deliveredTime=" + deliveredTime + '}';
    }

}