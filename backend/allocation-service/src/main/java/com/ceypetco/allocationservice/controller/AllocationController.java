package com.ceypetco.allocationservice.controller;

import com.ceypetco.allocationservice.AllocationServiceApplication;
import com.ceypetco.allocationservice.model.Quota;
import com.ceypetco.allocationservice.service.QuotaService;
import com.ceypetco.orderservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class AllocationController {

    private final KafkaTemplate<String, Order> allocationKafkaTemplate;
    private final KafkaTemplate<String, String> stringInventoryKafkaTemplate;

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

    @KafkaListener(topics = "quantityUpdateTopic", groupId = "ceypetco", containerFactory = "stringOrderKafkaListenerContainerFactory")
    void listener(String id) {
        Quota quota = quotaService.updateQuantities(id);

        if (quota == null) {
            AllocationServiceApplication.logger
                    .info("inventory-service : (order service -> dispatch service) couldn't update quantities");
        } else {
            AllocationServiceApplication.logger
                    .info("inventory-service : (order service -> dispatch service) updated quantities for" + id);
        }

    }

    public void initializeInventory(int initialQuantityOct92, int emergencyAllocationOct92, int initialQuantityOct95,
                                    int emergencyAllocationOct95, int initialQuantityAutoDiesel, int emergencyAllocationAutoDiesel, int initialQuantitySuperDiesel,
                                    int emergencyAllocationSuperDiesel) {
        quotaService.initializeInventory(initialQuantityOct92, emergencyAllocationOct92, initialQuantityOct95,
                emergencyAllocationOct95, initialQuantityAutoDiesel, emergencyAllocationAutoDiesel, initialQuantitySuperDiesel,
                emergencyAllocationSuperDiesel);
    }


}
