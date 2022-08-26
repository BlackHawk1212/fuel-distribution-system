package com.ceypetco.allocationservice.services;

import com.ceypetco.allocationservice.AllocationServiceApplication;
import com.ceypetco.allocationservice.models.Quota;
import com.ceypetco.allocationservice.repository.QuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QuotaService {
    @Autowired
    QuotaRepository quotaRepository;

    public Quota submitQuotaRecord(String id, Quantity quantityEnum, FuelType fuelType) {

        Integer quantityInLitres = 0;
        Integer allocatedSum = 0;
        Integer availableQuantity = 0;

        switch (quantityEnum) {
            case L3_300:
                quantityInLitres = 3300;
                break;
            case L6_600:
                quantityInLitres = 6600;
                break;
            case L13_200:
                quantityInLitres = 13200;
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + quantityEnum);
        }

        Quota previousQuota = quotaRepository.retrieveFinalQuota();

        previousQuota.setOrderId(id);
        previousQuota.setTimeStamp(LocalDateTime.now().toString());
        previousQuota.setTransactionQuantity(quantityInLitres);

        switch (fuelType) {
            case OCTANE92: {
                allocatedSum = previousQuota.getAllocatedQuotaOctane92();
                availableQuantity = previousQuota.getAvailableQuantityOctane92();

                if ((availableQuantity - allocatedSum) < quantityInLitres) {
                    return null;
                } else {
                    previousQuota.setAllocatedQuotaOctane92(allocatedSum + quantityInLitres);
                    AllocationServiceApplication.logger
                            .info("allocation-service : AllocatedQuantitySum of Octane 92 changed from " + allocatedSum
                                    + " to " + (allocatedSum + quantityInLitres));

                    return quotaRepository.save(previousQuota);
                }
            }
            case OCTANE95: {
                allocatedSum = previousQuota.getAllocatedQuotaOctane95();
                availableQuantity = previousQuota.getAvailableQuantityOctane95();

                if ((availableQuantity - allocatedSum) < quantityInLitres) {
                    return null;
                } else {
                    previousQuota.setAllocatedQuotaOctane95(allocatedSum + quantityInLitres);

                    AllocationServiceApplication.logger
                            .info("allocation-service : AllocatedQuantitySum of Octane 95 changed from " + allocatedSum
                                    + " to " + (allocatedSum + quantityInLitres));

                    return quotaRepository.save(previousQuota);
                }
            }
            case AUTO_DIESEL: {
                allocatedSum = previousQuota.getAllocatedQuotaAutoDiesel();
                availableQuantity = previousQuota.getAvailableQuantityAutoDiesel();

                if ((availableQuantity - allocatedSum) < quantityInLitres) {
                    return null;
                } else {
                    previousQuota.setAllocatedQuotaAutoDiesel(allocatedSum + quantityInLitres);

                    AllocationServiceApplication.logger
                            .info("allocation-service : AllocatedQuantitySum of Regular Diesel changed from " + allocatedSum
                                    + " to " + (allocatedSum + quantityInLitres));

                    return quotaRepository.save(previousQuota);
                }
            }
            case SUPER_DIESEL: {
                allocatedSum = previousQuota.getAllocatedQuotaSuperDiesel();
                availableQuantity = previousQuota.getAvailableQuantitySuperDiesel();

                if ((availableQuantity - allocatedSum) < quantityInLitres) {
                    return null;
                } else {
                    previousQuota.setAllocatedQuotaSuperDiesel(allocatedSum + quantityInLitres);

                    AllocationServiceApplication.logger
                            .info("inventory-service : AllocatedQuantitySum of Super Diesel changed from " + allocatedSum
                                    + " to " + (allocatedSum + quantityInLitres));

                    return quotaRepository.save(previousQuota);
                }
            }
            default: {
                return null;
            }
        }
    }

    public Quota updateQuantities(String id) {
        return quotaRepository.retrieveSpecificRecord(id);
    }

    public void initializeInventory(int initialQuantityO92, int emergencyAllocationO92, int initialQuantityO95,
                                    int emergencyAllocationO95, int initialQuantityRD, int emergencyAllocationRD, int initialQuantitySD,
                                    int emergencyAllocationSD) {
        Quota initialQuota = new Quota(LocalDateTime.now().toString(), "00000000");

        initialQuota.setAllocatedQuotaOctane92(emergencyAllocationO92);
        initialQuota.setAvailableQuantityOctane92(initialQuantityO92);

        initialQuota.setAllocatedQuotaOctane95(emergencyAllocationO95);
        initialQuota.setAvailableQuantityOctane95(initialQuantityO95);

        initialQuota.setAllocatedQuotaAutoDiesel(emergencyAllocationRD);
        initialQuota.setAvailableQuantityAutoDiesel(initialQuantityRD);

        initialQuota.setAllocatedQuotaAutoDiesel(emergencyAllocationSD);
        initialQuota.setAvailableQuantityAutoDiesel(initialQuantitySD);

        Quota quota = quotaRepository.save(initialQuota);
    }

}
