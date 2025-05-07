package com.github.konarjg.BackendAPI.service;

import com.github.konarjg.BackendAPI.entity.Order;
import com.github.konarjg.BackendAPI.entity.OrderState;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class EmergencyService {
    private final Random random = new Random();
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private final OrderService orderService;

    public EmergencyService(OrderService orderService) {
        this.orderService = orderService;
        executor.scheduleAtFixedRate(this::generateRandomEmergencies, 10000, 10000, TimeUnit.MILLISECONDS);
    }

    public void generateRandomEmergencies() {
        Order order = orderService.findRandom();

        if (order == null) {
            return;
        }

        int state = random.nextInt(0, 3);

        switch (state) {
            case 0:
                break;

            case 1:
                order.setState(OrderState.DELAYED);
                break;

            case 2:
                order.setState(OrderState.LOST);
                break;
        }

        orderService.save(order);
    }
}
