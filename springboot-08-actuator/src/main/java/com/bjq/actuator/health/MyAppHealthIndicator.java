package com.bjq.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class MyAppHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        //Health.up().build()代表健康
        return Health.down().withDetail("msg","服务异常").build();
    }
}
