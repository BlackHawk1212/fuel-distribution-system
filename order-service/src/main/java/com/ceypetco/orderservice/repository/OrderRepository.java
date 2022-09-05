package com.ceypetco.orderservice.repository;

import com.ceypetco.orderservice.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {

}