package com.ceypetco.allocationservice.repository;

import com.ceypetco.allocationservice.model.Quota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;

public class QuotaRepositoryImpl implements QuotaRepositoryInterface{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Quota retrieveFinalQuota() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "timeStamp")).limit(1);
        return mongoTemplate.findOne(query, Quota.class);
    }

    @Override
    public Quota retrieveSpecificRecord(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("orderId").is(id));
        Quota quotaById = mongoTemplate.findOne(query, Quota.class);

        Quota quotaByTimestamp = this.retrieveFinalQuota();

        Quota newQuota = quotaByTimestamp.clone();

        int quantity = quotaById.getTransactionQuantity();
        newQuota.setTransactionQuantity(quantity);

        newQuota.setTimeStamp(LocalDateTime.now().toString());
        newQuota.setOrderId(id);

        String fuelTypeString = id.substring(id.length() - 2);
        updateQuantities(newQuota, fuelTypeString, quantity);
        System.out.println("Created new quota record :" + newQuota);
        return mongoTemplate.save(newQuota);

    }

    private void updateQuantities(Quota quota, String fuelTypeString, int quantity) {
        switch (fuelTypeString) {
            case "Oct92" -> {
                quota.setAllocatedQuotaOctane92(quota.getAllocatedQuotaOctane92() - quantity);
                quota.setAvailableQuantityOctane92(quota.getAvailableQuantityOctane92() - quantity);
            }
            case "Oct95" -> {
                quota.setAllocatedQuotaOctane95(quota.getAllocatedQuotaOctane95() - quantity);
                quota.setAvailableQuantityOctane95(quota.getAvailableQuantityOctane95() - quantity);
            }
            case "AutoD" -> {
                quota.setAllocatedQuotaAutoDiesel(quota.getAllocatedQuotaAutoDiesel() - quantity);
                quota.setAvailableQuantityAutoDiesel(quota.getAvailableQuantityAutoDiesel() - quantity);
            }
            case "SuperD" -> {
                quota.setAllocatedQuotaSuperDiesel(quota.getAllocatedQuotaSuperDiesel() - quantity);
                quota.setAvailableQuantitySuperDiesel(quota.getAvailableQuantitySuperDiesel() - quantity);
            }
            default -> System.out.println("Fuel Type mismatched");
        }
    }

}