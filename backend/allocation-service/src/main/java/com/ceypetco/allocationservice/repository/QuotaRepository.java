package com.ceypetco.allocationservice.repository;

import com.ceypetco.allocationservice.model.Quota;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotaRepository extends MongoRepository<Quota,String>, QuotaRepositoryInterface {

}