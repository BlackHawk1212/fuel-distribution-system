package com.ceypetco.allocationservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("quota")
public class Quota implements Cloneable{

    @Id
    private String timeStamp;

    private String orderId;
    private Integer transactionQuantity;

//    OCTANE_92
    private Integer allocatedQuotaOctane92;
    private Integer availableQuantityOctane92;

//    OCTANE_95
    private Integer allocatedQuotaOctane95;
    private Integer availableQuantityOctane95;

//    AUTO_DIESEL
    private Integer allocatedQuotaAutoDiesel;
    private Integer availableQuantityAutoDiesel;

//    SUPER_DIESEL
    private Integer allocatedQuotaSuperDiesel;
    private Integer availableQuantitySuperDiesel;

    public Quota() {

    }

    public Quota(String timeStamp, String orderId) {
        this.timeStamp = timeStamp;
        this.orderId = orderId;
    }

    @Override
    public Quota clone() {
        Quota quota = new Quota();
        try {
            return (Quota) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Error in Cloning " + e);
        }
        return quota;
    }

    public Quota(String timeStamp, String orderId,
                 Integer transactionQuantity, Integer allocatedQuotaOctane92,
                 Integer availableQuantityOctane92, Integer allocatedQuotaOctane95,
                 Integer availableQuantityOctane95, Integer allocatedQuotaAutoDiesel,
                 Integer availableQuantityAutoDiesel, Integer allocatedQuotaSuperDiesel,
                 Integer availableQuantitySuperDiesel) {
        this.timeStamp = timeStamp;
        this.orderId = orderId;
        this.transactionQuantity = transactionQuantity;
        this.allocatedQuotaOctane92 = allocatedQuotaOctane92;
        this.availableQuantityOctane92 = availableQuantityOctane92;
        this.allocatedQuotaOctane95 = allocatedQuotaOctane95;
        this.availableQuantityOctane95 = availableQuantityOctane95;
        this.allocatedQuotaAutoDiesel = allocatedQuotaAutoDiesel;
        this.availableQuantityAutoDiesel = availableQuantityAutoDiesel;
        this.allocatedQuotaSuperDiesel = allocatedQuotaSuperDiesel;
        this.availableQuantitySuperDiesel = availableQuantitySuperDiesel;
    }

    @JsonProperty("timeStamp")
    public String getTimeStamp() {
        return timeStamp;
    }

    @JsonProperty("timeStamp")
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @JsonProperty("orderId")
    public String getOrderId() {
        return orderId;
    }

    @JsonProperty("orderId")
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @JsonProperty("transactionQuantity")
    public Integer getTransactionQuantity() {
        return transactionQuantity;
    }

    @JsonProperty("transactionQuantity")
    public void setTransactionQuantity(Integer transactionQuantity) {
        this.transactionQuantity = transactionQuantity;
    }

    @JsonProperty("allocatedQuotaOctane92")
    public Integer getAllocatedQuotaOctane92() {
        return allocatedQuotaOctane92;
    }

    @JsonProperty("allocatedQuotaOctane92")
    public void setAllocatedQuotaOctane92(Integer allocatedQuotaOctane92) {
        this.allocatedQuotaOctane92 = allocatedQuotaOctane92;
    }

    @JsonProperty("availableQuantityOctane92")
    public Integer getAvailableQuantityOctane92() {
        return availableQuantityOctane92;
    }

    @JsonProperty("availableQuantityOctane92")
    public void setAvailableQuantityOctane92(Integer availableQuantityOctane92) {
        this.availableQuantityOctane92 = availableQuantityOctane92;
    }

    @JsonProperty("allocatedQuotaOctane95")
    public Integer getAllocatedQuotaOctane95() {
        return allocatedQuotaOctane95;
    }

    @JsonProperty("allocatedQuotaOctane95")
    public void setAllocatedQuotaOctane95(Integer allocatedQuotaOctane95) {
        this.allocatedQuotaOctane95 = allocatedQuotaOctane95;
    }

    @JsonProperty("availableQuantityOctane95")
    public Integer getAvailableQuantityOctane95() {
        return availableQuantityOctane95;
    }

    @JsonProperty("availableQuantityOctane95")
    public void setAvailableQuantityOctane95(Integer availableQuantityOctane95) {
        this.availableQuantityOctane95 = availableQuantityOctane95;
    }

    @JsonProperty("allocatedQuotaAutoDiesel")
    public Integer getAllocatedQuotaAutoDiesel() {
        return allocatedQuotaAutoDiesel;
    }

    @JsonProperty("allocatedQuotaAutoDiesel")
    public void setAllocatedQuotaAutoDiesel(Integer allocatedQuotaAutoDiesel) {
        this.allocatedQuotaAutoDiesel = allocatedQuotaAutoDiesel;
    }

    @JsonProperty("availableQuantityAutoDiesel")
    public Integer getAvailableQuantityAutoDiesel() {
        return availableQuantityAutoDiesel;
    }

    @JsonProperty("availableQuantityAutoDiesel")
    public void setAvailableQuantityAutoDiesel(Integer availableQuantityAutoDiesel) {
        this.availableQuantityAutoDiesel = availableQuantityAutoDiesel;
    }

    @JsonProperty("allocatedQuotaSuperDiesel")
    public Integer getAllocatedQuotaSuperDiesel() {
        return allocatedQuotaSuperDiesel;
    }

    @JsonProperty("allocatedQuotaSuperDiesel")
    public void setAllocatedQuotaSuperDiesel(Integer allocatedQuotaSuperDiesel) {
        this.allocatedQuotaSuperDiesel = allocatedQuotaSuperDiesel;
    }

    @JsonProperty("availableQuantitySuperDiesel")
    public Integer getAvailableQuantitySuperDiesel() {
        return availableQuantitySuperDiesel;
    }

    @JsonProperty("availableQuantitySuperDiesel")
    public void setAvailableQuantitySuperDiesel(Integer availableQuantitySuperDiesel) {
        this.availableQuantitySuperDiesel = availableQuantitySuperDiesel;
    }

    @Override
    public String toString() {
        return "Quota [timeStamp=" + timeStamp + ", orderId=" + orderId + ", transactionQuantity=" + transactionQuantity
                + ", allocatedQuotaSumO92=" + allocatedQuotaOctane92 + ", availableQuantityO92=" + availableQuantityOctane92
                + ", allocatedQuotaSumO95=" + allocatedQuotaOctane95 + ", availableQuantityO95=" + availableQuantityOctane95
                + ", allocatedQuotaSumRD=" + allocatedQuotaAutoDiesel + ", availableQuantityRD=" + availableQuantityAutoDiesel
                + ", allocatedQuotaSumSD=" + allocatedQuotaSuperDiesel + ", availableQuantitySD=" + availableQuantitySuperDiesel + "]";
    }

}
