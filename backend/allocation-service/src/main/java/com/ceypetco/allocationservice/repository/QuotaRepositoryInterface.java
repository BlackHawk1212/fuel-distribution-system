package com.ceypetco.allocationservice.repository;

import com.ceypetco.allocationservice.model.Quota;

public interface QuotaRepositoryInterface {
    Quota retrieveFinalQuota();
    Quota retrieveSpecificRecord(String id);
}