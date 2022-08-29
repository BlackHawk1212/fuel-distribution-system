package com.ceypetco.scheduleservice.controller;

import com.ceypetco.orderservice.models.Order;
import com.ceypetco.scheduleservice.dto.ScheduleDateDTO;
import com.ceypetco.scheduleservice.model.Schedule;
import com.ceypetco.scheduleservice.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    private final KafkaTemplate<String, Order> scheduleKafkaTemplate;
    private final KafkaTemplate<String, ScheduleDateDTO> scheduleDateKafkaTemplate;

    public ScheduleController(KafkaTemplate<String, Order> scheduleKafkaTemplate,
                              KafkaTemplate<String, ScheduleDateDTO> scheduleDateKafkaTemplate) {
        this.scheduleKafkaTemplate = scheduleKafkaTemplate;
        this.scheduleDateKafkaTemplate = scheduleDateKafkaTemplate;
    }

    @KafkaListener(topics = "scheduleCreateTopic", groupId = "ceypetco", containerFactory = "scheduleKafkaListenerContainerFactory")
    void listener(Order order) {
        System.out.println("Received to schedule service " + order.toString());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime randomDateTime = now.plusMinutes(ThreadLocalRandom.current().nextInt(59));

        System.out.println("Random date is " + randomDateTime);

        order.setScheduled(true);
        order.setScheduledTime(randomDateTime);
        scheduleKafkaTemplate.send("scheduleSubmitTopic", "Order for " + order.getId() + " scheduled", order);

        Schedule schedule = new Schedule(LocalDateTime.now().toString(), order.getId(), randomDateTime);
        Schedule s = scheduleService.submitScheduleRecord(schedule);

        int quantity = Integer.parseInt((order.getQuantity().toString()).replace("L", "").replace("_", ""));
        System.out.println("Quantity is :" + quantity);

        ScheduleDateDTO scheduleDateDTO = new ScheduleDateDTO(order.getId(), order.getScheduledTime(), order.getStationId(), quantity);
        System.out.println("Forwarding ScheduleDateDTO " + scheduleDateDTO + " to dispatching service. ");
        scheduleDateKafkaTemplate.send("dispatchCreateTopic",
                "Kafka : Submitted order " + order.getId() + " to dispatchCreateTopic", scheduleDateDTO);

    }

}
