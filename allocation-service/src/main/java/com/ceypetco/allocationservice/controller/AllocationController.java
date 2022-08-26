package com.ceypetco.allocationservice.controller;

import com.ceypetco.allocationservice.AllocationServiceApplication;
import com.ceypetco.allocationservice.models.Quota;
import com.ceypetco.allocationservice.services.QuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class AllocationController {
    private KafkaTemplate<String, Order> allocationKafkaTemplate;
    private KafkaTemplate<String, String> stringInventoryKafkaTemplate;

    public AllocationController(KafkaTemplate<String, Order> allocationKafkaTemplate, KafkaTemplate<String, String> stringInventoryKafkaTemplate) {
        this.allocationKafkaTemplate = allocationKafkaTemplate;
        this.stringInventoryKafkaTemplate = stringInventoryKafkaTemplate;
    }

    @Autowired
    QuotaService quotaService;

    @KafkaListener(topics = "orderCreateTopic", groupId = "ceypetco", containerFactory = "allocationKafkaListenerContainerFactory")
    void listener(Order order) {

        Quota quota = quotaService.submitQuotaRecord(order.getId(), order.getQuantity(), order.getFuelType());

        if (quota == null) {
            AllocationServiceApplication.logger
                    .info("inventory-service : Can't suffice order :" + order.getId() + " due to running out of stock");
        } else {
            allocationKafkaTemplate.send("inventorySubmitTopic", "Order for " + order.getId() + " allocated", order);
        }
    }

    @KafkaListener(topics = "quantityUpdateTopic", groupId = "cpc", containerFactory = "sOrderKafkaListenerContainerFactory")
    void listener(String id) {
        Quota quota = quotaService.updateQuantities(id);

        if (quota == null) {
            AllocationServiceApplication.logger
                    .info("inventory-service : (order serive -> dispatch service) couldn't update quantities");
        } else {
            AllocationServiceApplication.logger
                    .info("inventory-service : (order serive -> dispatch service) updated quantities for" + id);
        }

    }

    public void initializeInventory(int initialQuantityO92, int emergencyAllocationO92, int initialQuantityO95,
                                    int emergencyAllocationO95, int initialQuantityRD, int emergencyAllocationRD, int initialQuantitySD,
                                    int emergencyAllocationSD) {
        quotaService.initializeInventory(initialQuantityO92, emergencyAllocationO92, initialQuantityO95,
                emergencyAllocationO95, initialQuantityRD, emergencyAllocationRD, initialQuantitySD,
                emergencyAllocationSD);
    }

}
