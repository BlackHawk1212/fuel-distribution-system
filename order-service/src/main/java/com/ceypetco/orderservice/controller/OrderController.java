package com.ceypetco.orderservice.controller;

import com.ceypetco.orderservice.OrderServiceApplication;
import com.ceypetco.orderservice.models.Order;
import com.ceypetco.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/services")
public class OrderController {

    @Autowired
    OrderService orderService;

    private final KafkaTemplate<String, Order> orderKafkaTemplate;
    private final KafkaTemplate<String, String> sOrderKafkaTemplate;

    public OrderController(KafkaTemplate<String, Order> orderKafkaTemplate,
                           KafkaTemplate<String, String> sOrderKafkaTemplate) {
        this.orderKafkaTemplate = orderKafkaTemplate;
        this.sOrderKafkaTemplate = sOrderKafkaTemplate;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    @PostMapping
    public Order submitOrder(@RequestBody Order order) {
        Order o = orderService.submitOrder(order.getStationId(), order);
        orderKafkaTemplate.send("orderCreateTopic", "Kafka : Submitted order " + o.getId() + " to orderSubmitTopic", o);

        OrderServiceApplication.logger.info("order-service : Submitted order " + o.getId() + " to orderSubmitTopic");
        return o;
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public String checkOrderStatus(@PathVariable(value = "id") String id) {
        String trimmedId = id.trim();
        String status = orderService.checkOrderStatus(trimmedId);
        OrderServiceApplication.logger.info("order-service : Status for " + id + " is : " + status);
        return status;

    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.POST)
    public String confirmOrderReceive(@PathVariable(value = "id") String id) {
        String status = orderService.confirmOrderReceive(id);
        OrderServiceApplication.logger.info("order-service : Confirmed order receival of " + id);
        return status;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public Object fetch() {
        List<Order> orders = orderService.fetchAllOrders();

        if (orders == null) {
            return "Order list is empty.";
        } else {
            OrderServiceApplication.logger.info("order-service : Fetching all orders");
            return orders;
        }
    }

    @KafkaListener(topics = "inventorySubmitTopic", groupId = "ceypetco", containerFactory = "orderKafkaListenerContainerFactory")
    void inventoryListener(Order order) {
        Order odr = orderService.updateOrderAllocation(order.getId());
        if (odr == null) {
            OrderServiceApplication.logger
                    .info("order-service : (From inventory service) Error in updating order allocation status");
        } else {
            OrderServiceApplication.logger
                    .info("order-service : (From inventory service) Updated allocation status to: " + odr);
            orderKafkaTemplate.send("scheduleCreateTopic",
                    "Kafka : Submitted order " + odr.getId() + " to scheduleSubmitTopic", odr);
        }
    }

    @KafkaListener(topics = "scheduleSubmitTopic", groupId = "ceypetco", containerFactory = "orderKafkaListenerContainerFactory")
    void scheduleListener(Order order) {
        Order o = orderService.updateOrderSchedule(order.getId(), order.getScheduledTime());
        if (o == null) {
            OrderServiceApplication.logger
                    .info("order-service : (From schedule service) Error in updating order schedule status");
        } else {
            OrderServiceApplication.logger
                    .info("order-service : (From schedule service) Updated schedule status to: " + o);
        }
    }

    @KafkaListener(topics = "dispatchSubmitTopic", groupId = "ceypetco", containerFactory = "sKafkaListenerContainerFactory")
    void dispatchListener(String id) {
        Order o = orderService.updateOrderDispatch(id, LocalDateTime.now());
        if (o == null) {
            OrderServiceApplication.logger
                    .info("order-service : (From dispatch service) Error in updating order dispatch status");
        } else {
            OrderServiceApplication.logger
                    .info("order-service : (From dispatch service) Updated dispatch status to: " + o);
        }
        sOrderKafkaTemplate.send("quantityUpdateTopic", "Kafka : Submitted orderId " + id + " to quantityUpdateTopic",
                id);
    }

}
