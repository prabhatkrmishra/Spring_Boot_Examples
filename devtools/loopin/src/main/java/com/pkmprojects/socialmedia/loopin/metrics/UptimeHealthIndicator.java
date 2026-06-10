package com.pkmprojects.socialmedia.loopin.metrics;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.time.Duration;

@Component("uptime")
public class UptimeHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        long uptimeMs = ManagementFactory.getRuntimeMXBean().getUptime();
        Duration duration = Duration.ofMillis(uptimeMs);

        return Health.up()
                .withDetail("uptimeMs", uptimeMs)
                .withDetail("uptime", formatUpTime(duration))
                .build();
    }

    private String formatUpTime(Duration duration){
        long days = duration.toDays();
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        return "%dd %dh %dm %ds"
                .formatted(days, hours, minutes, seconds);
    }
}
