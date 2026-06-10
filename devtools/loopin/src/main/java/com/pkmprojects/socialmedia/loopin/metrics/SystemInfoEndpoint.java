package com.pkmprojects.socialmedia.loopin.metrics;

import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@Endpoint(id = "system-info")
@Component
public class SystemInfoEndpoint {

    @ReadOperation
    public Map<String, Object> info() {

        Runtime runtime = Runtime.getRuntime();

        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        double memoryUsagePercent =
                totalMemory == 0
                        ? 0.0
                        : (double) usedMemory / totalMemory * 100;

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("runtime", Map.of(
                "startedAt", Instant.ofEpochMilli(runtimeMXBean.getStartTime()),
                "uptimeMs", runtimeMXBean.getUptime(),
                "uptimeSeconds", runtimeMXBean.getUptime() / 1000
        ));

        response.put("spring", Map.of(
                "bootVersion", SpringBootVersion.getVersion()
        ));

        response.put("jvm", Map.of(
                "name", System.getProperty("java.vm.name"),
                "vendor", System.getProperty("java.vendor"),
                "version", System.getProperty("java.version")
        ));

        response.put("os", Map.of(
                "name", System.getProperty("os.name"),
                "version", System.getProperty("os.version"),
                "architecture", System.getProperty("os.arch"),
                "processors", runtime.availableProcessors()
        ));

        response.put("memory", Map.of(
                "usedMb", usedMemory / 1024 / 1024,
                "freeMb", freeMemory / 1024 / 1024,
                "totalMb", totalMemory / 1024 / 1024,
                "maxMb", maxMemory / 1024 / 1024,
                "usagePercent", Math.round(memoryUsagePercent * 100.0) / 100.0
        ));

        response.put("threads", Map.of(
                "live", threadMXBean.getThreadCount(),
                "daemon", threadMXBean.getDaemonThreadCount(),
                "peak", threadMXBean.getPeakThreadCount()
        ));

        return response;
    }
}