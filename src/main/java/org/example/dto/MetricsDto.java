package org.example.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class MetricsDto {
    private UUID id;
    private ZonedDateTime time;
    private BigDecimal value;
}