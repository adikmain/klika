package org.example.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class DeviceDetailsDto {
    private UUID id;
    private String name;
    private ZonedDateTime lastRebootTime;
    private List<MetricsDto> lastMetrics;
}
