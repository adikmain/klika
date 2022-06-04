package org.example.client;

import org.example.entity.Metric;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface MetricClient {
    List<Metric> getAllByDeviceIdIn(Collection<UUID> deviceIds);
}
