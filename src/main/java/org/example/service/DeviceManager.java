package org.example.service;

import org.example.client.DeviceClient;
import org.example.client.MetricClient;
import org.example.dto.DeviceDetailsDto;
import org.example.dto.MetricsDto;
import org.example.entity.Device;
import org.example.entity.Metric;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class DeviceManager {
    DeviceClient deviceClient;
    MetricClient metricClient;

    public List<DeviceDetailsDto> getDevicesDetails(Set<UUID> ids) {
        List<DeviceDetailsDto> detailsDto = new ArrayList<>();

        List<Device> devices = deviceClient.getAllByIdIn(ids);

        devices.forEach(device -> {
            List<Metric> deviceMetrics = metricClient
                    .getAllByDeviceIdIn(List.of(device.getId()));

            List<Metric> onlyLastMetrics = deviceMetrics
                    .stream()
                    .filter(metric -> metric.getTime().isAfter(device.getLastRebootTime()) || metric.getTime().isEqual(device.getLastRebootTime()))
                    .toList();

            List<MetricsDto> lastMetricsDTO = onlyLastMetrics
                    .stream()
                    .map(this::toMetricDTO)
                    .toList();

            detailsDto.add(toDeviceDetailsDTO(device, lastMetricsDTO));
        });

        return detailsDto;
    }

    private DeviceDetailsDto toDeviceDetailsDTO(Device device, List<MetricsDto> lastModifiedMetrics) {
        DeviceDetailsDto dto = new DeviceDetailsDto();

        dto.setId(device.getId());
        dto.setName(device.getName());
        dto.setLastRebootTime(device.getLastRebootTime());
        dto.setLastMetrics(lastModifiedMetrics);

        return dto;
    }

    private MetricsDto toMetricDTO(Metric metric) {
        MetricsDto metricsDto = new MetricsDto();

        metric.setDeviceId(metric.getDeviceId());
        metric.setId(metric.getId());
        metric.setTime(metric.getTime());
        metric.setValue(metric.getValue());

        return metricsDto;
    }
}

