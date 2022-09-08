package com.ceypetco.allocationservice.service;

import com.ceypetco.allocationservice.model.Quota;
import com.ceypetco.orderservice.model.FuelType;
import com.ceypetco.orderservice.model.Quantity;
import org.springframework.stereotype.Service;


public interface QuotaServiceInterface {

    Quota submitQuotaRecord(String id, Quantity quantityEnum, FuelType fuelType);

    public void initializeInventory(int initialQuantityOct92, int emergencyAllocationOct92, int initialQuantityOct95,
                                    int emergencyAllocationOct95, int initialQuantityAutoDiesel, int emergencyAllocationAutoDiesel, int initialQuantitySuperDiesel,
                                    int emergencyAllocationSuperDiesel);

    Quota updateQuantities(String id);

}
