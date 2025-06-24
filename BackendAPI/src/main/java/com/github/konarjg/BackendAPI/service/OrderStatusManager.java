package com.github.konarjg.BackendAPI.service;

import com.github.konarjg.BackendAPI.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrderStatusManager {
    private static final Logger log = LoggerFactory.getLogger(OrderStatusManager.class);
    private final OrderService orderService;

    @Value("${app.shipping.delay.min-ms}")
    private long shippingDelayMin;
    @Value("${app.shipping.delay.max-ms}")
    private long shippingDelayMax;
    @Value("${app.outcome.time.delivered-max-ms}")
    private long deliveredMax;
    @Value("${app.outcome.time.delayed-max-ms}")
    private long delayedMax;

    public OrderStatusManager(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(fixedDelayString = "${app.scheduler.fixed-delay-ms}")
    public void checkAndProcessOrders() {
        log.info("Scheduler running: Checking for orders in PREPARING state...");
        List<Order> preparingOrders = orderService.findAllByStatus(Order.Status.PREPARING);

        if (preparingOrders.isEmpty()) {
            log.info("No PREPARING orders found.");
            return;
        }

        log.info("Found {} PREPARING orders. Starting async processing.", preparingOrders.size());
        for (Order order : preparingOrders) {
            processOrderLifecycle(order);
        }
    }

    @Async
    public void processOrderLifecycle(Order order) {
        try {
            log.info("[Order #{}] Processing lifecycle.", order.getOrderId());

            long shippingTime = ThreadLocalRandom.current().nextLong(shippingDelayMin, shippingDelayMax);
            log.info("[Order #{}] Status will be updated to SHIPPING in {}ms.", order.getOrderId(), shippingTime);
            Thread.sleep(shippingTime);
            orderService.updateStatus(order.getOrderId(), Order.Status.SHIPPING);
            log.info("[Order #{}] Status updated to SHIPPING.", order.getOrderId());

            long outcomeTime = ThreadLocalRandom.current().nextLong(-5000, delayedMax + 10000); // Generate a wide range
            log.info("[Order #{}] Generated outcome time: {}ms.", order.getOrderId(), outcomeTime);

            if (outcomeTime < 0 || outcomeTime > delayedMax) {
                log.info("[Order #{}] Outcome: LOST.", order.getOrderId());
                orderService.updateStatus(order.getOrderId(), Order.Status.LOST);
            } else if (outcomeTime > deliveredMax) {
                log.info("[Order #{}] Outcome: DELAYED.", order.getOrderId());
                orderService.updateStatus(order.getOrderId(), Order.Status.DELAYED);
            } else {
                log.info("[Order #{}] Outcome: DELIVERED.", order.getOrderId());
                orderService.updateStatus(order.getOrderId(), Order.Status.DELIVERED);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("[Order #{}] Processing was interrupted.", order.getOrderId(), e);
        } catch (Exception e) {
            log.error("[Order #{}] An unexpected error occurred during processing.", order.getOrderId(), e);
        }
    }
}
