package com.ceypetco.allocationservice.repository;

import com.ceypetco.allocationservice.models.Quota;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuotaRepository extends MongoRepository<Quota,String>, QuotaRepositoryInterface {

}