package com.ceypetco.orderservice.service;

import com.ceypetco.orderservice.models.FuelType;
import com.ceypetco.orderservice.models.Order;
import com.ceypetco.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Order submitOrder(String stationId, Order order) {
        String createdRefId = createId(stationId, order.getFuelType());
        order.setId(createdRefId);
        order.setReservedTime(LocalDateTime.now());
        order.setReserved(true);
        return orderRepository.save(order);
    }

    private String createId(String stationId, FuelType fuelType) {
        int uniqueId = (int) ((new Date().getTime() / 10000000L) % Integer.MAX_VALUE);
        return stationId.substring(0, 4) + stationId.charAt(stationId.length() - 1) + uniqueId + getFId(fuelType);
    }

    private String getFId(FuelType fId) {
        switch (fId) {
            case OCTANE92:
                return "92";
            case OCTANE95:
                return "95";
            case AUTO_DIESEL:
                return "AD";
            case SUPER_DIESEL:
                return "SD";
            default:
                System.out.println("No such FuelType Found. Can't create fId");
                throw new IllegalArgumentException();
        }
    }

    public String checkOrderStatus(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(this::getStatus).orElse("Please check the reference number and try again!");
    }

    private String getStatus(Order order) {
        if (order.isDelivered()) {
            LocalDateTime deliveredDateTime = order.getDeliveredTime();
            return "Delivery completed on " + (DateTimeFormatter.ISO_LOCAL_DATE).format(deliveredDateTime) + " at "
                    + (DateTimeFormatter.ISO_LOCAL_TIME).format(deliveredDateTime) + ". Thank you!";
        } else if (order.isDispatched()) {
            LocalDateTime dispatchedDateTime = order.getDispatchedTime();
            return "Dispatching completed on " + (DateTimeFormatter.ISO_LOCAL_DATE).format(dispatchedDateTime) + " at "
                    + (DateTimeFormatter.ISO_LOCAL_TIME).format(dispatchedDateTime) + ". Await delivery!";
        } else if (order.isScheduled()) {
            LocalDateTime scheduleDateTime = order.getScheduledTime();
            return "Scheduling completed on " + (DateTimeFormatter.ISO_LOCAL_DATE).format(scheduleDateTime) + " at "
                    + (DateTimeFormatter.ISO_LOCAL_TIME).format(scheduleDateTime) + ". Await dispatch!";
        } else if (order.isQtyAllocated()) {
            LocalDateTime allocatedDateTime = order.getAllocatedTime();
            return "Allocation completed on " + (DateTimeFormatter.ISO_LOCAL_DATE).format(allocatedDateTime) + " at "
                    + (DateTimeFormatter.ISO_LOCAL_TIME).format(allocatedDateTime) + ". Await scheduling!";
        } else if (order.isReserved()) {
            LocalDateTime reservedDateTime = order.getReservedTime();
            return "Reservation completed on " + (DateTimeFormatter.ISO_LOCAL_DATE).format(reservedDateTime) + " at "
                    + (DateTimeFormatter.ISO_LOCAL_TIME).format(reservedDateTime) + ". Await allocation!";
        } else {
            return "Something went wrong...";
        }
    }

    public String confirmOrderReceive(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(this::updateDeliveredField).orElse("Please check the reference number and try again!");
    }

    private String updateDeliveredField(Order order) {
        order.setDelivered(true);
        order.setDeliveredTime(LocalDateTime.now());
        orderRepository.save(order);
        return "Order Receive is confirmed. Thank you!";
    }

    public List<Order> fetchAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrderAllocation(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(this::updateAllocatedField).orElse(null);
    }

    private Order updateAllocatedField(Order order) {
        order.setQtyAllocated(true);
        order.setAllocatedTime(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public Order updateOrderSchedule(String id, LocalDateTime scheduledDateTime) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(value -> updateScheduleField(value, scheduledDateTime)).orElse(null);
    }

    private Order updateScheduleField(Order order, LocalDateTime scheduledDateTime) {
        order.setScheduled(true);
        order.setScheduledTime(scheduledDateTime);
        return orderRepository.save(order);
    }

    public Order updateOrderDispatch(String id, LocalDateTime dispatchedDateTime) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(value -> updateDispatchField(value, dispatchedDateTime)).orElse(null);
    }

    private Order updateDispatchField(Order order, LocalDateTime dispatchedDateTime) {
        order.setDispatched(true);
        order.setDispatchedTime(dispatchedDateTime);
        return orderRepository.save(order);
    }

}