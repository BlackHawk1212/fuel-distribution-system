package com.ceypetco.orderservice.config;

import com.ceypetco.orderservice.OrderServiceApplication;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    @Bean
    public NewTopic getNewOrderCreateTopic() {
        OrderServiceApplication.logger.info("order-service :Created orderCreateTopic topic");
        return TopicBuilder.name("orderCreateTopic").build();
    }

    @Bean
    public NewTopic getNewInventorySubmitTopic() {
        OrderServiceApplication.logger.info("order-service : Created inventorySubmitTopic topic");
        return TopicBuilder.name("inventorySubmitTopic").build();
    }

    @Bean
    public NewTopic getNewScheduleCreateTopic() {
        OrderServiceApplication.logger.info("order-service : Created scheduleCreateTopic topic");
        return TopicBuilder.name("scheduleCreateTopic").build();
    }

    @Bean
    public NewTopic getNewScheduleSubmitTopic() {
        OrderServiceApplication.logger.info("order-service : Created scheduleSubmitTopic topic");
        return TopicBuilder.name("scheduleSubmitTopic").build();
    }

    @Bean
    public NewTopic getNewDispatchSubmitTopic() {
        OrderServiceApplication.logger.info("order-service : Created dispatchSubmitTopic topic");
        return TopicBuilder.name("dispatchSubmitTopic").build();
    }

    @Bean
    public NewTopic getNewQuantityUpdateTopic() {
        OrderServiceApplication.logger.info("order-service : Created quantityUpdateTopic topic");
        return TopicBuilder.name("quantityUpdateTopic").build();
    }

}
