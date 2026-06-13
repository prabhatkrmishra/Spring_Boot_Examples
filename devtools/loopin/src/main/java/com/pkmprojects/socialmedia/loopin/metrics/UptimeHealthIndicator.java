package com.pkmprojects.socialmedia.loopin.metrics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.time.Duration;

@Component("uptime")
public class UptimeHealthIndicator implements HealthIndicator {

    Logger log = LoggerFactory.getLogger(UptimeHealthIndicator.class);

    @Override
    public Health health() {
        long uptimeMs = ManagementFactory.getRuntimeMXBean().getUptime();
        Duration duration = Duration.ofMillis(uptimeMs);

        log.info("== Creating custom health actuator ==");

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

        log.warn("== Formatting custom health actuator ==");
        log.debug("== Debug custom health actuator ==");

        return "%dd %dh %dm %ds"
                .formatted(days, hours, minutes, seconds);
    }
}
