package org.example.entity;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Device {
    private UUID id;
    private String name;
    private ZonedDateTime lastRebootTime;
    private List<Metric> metrics;
}
