package com.ceypetco.allocationservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("quota")
@Data
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

    public Quota(String timeStamp, String orderId) {
        this.timeStamp = timeStamp;
        this.orderId = orderId;
    }

    public Quota() {}

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


}
