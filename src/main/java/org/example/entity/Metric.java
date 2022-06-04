package org.example.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class Metric {
    private UUID id;
    private UUID deviceId;
    private ZonedDateTime time;
    private BigDecimal value;
}
