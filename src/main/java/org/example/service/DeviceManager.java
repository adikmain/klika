package org.example.service;

import org.example.client.DeviceClient;
import org.example.client.MetricClient;
import org.example.dto.DeviceDetailsDto;
import org.example.dto.MetricsDto;
import org.example.entity.Device;
import org.example.entity.Metric;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class DeviceManager {
    DeviceClient deviceClient;
    MetricClient metricClient;

    public List<DeviceDetailsDto> getDevicesDetails(Set<UUID> ids) {
        List<Device> devices = deviceClient.getAllByIdIn(ids);
        List<Metric> metrics = metricClient.getAllByDeviceIdIn(ids);

        var metricsByDeviceId = metrics.stream().collect(Collectors.groupingBy(Metric::getDeviceId));

        return devices
                .stream()
                .map(device -> toDeviceDetailsDTO(device, filterLastMetrics(metricsByDeviceId.get(device.getId()), device)))
                .collect(Collectors.toList());
    }

    private List<MetricsDto> filterLastMetrics(List<Metric> metrics, Device device) {
        return metrics
                .stream()
                .filter(metric -> !metric.getTime().isBefore(device.getLastRebootTime()))
                .map(this::toMetricDTO)
                .collect(Collectors.toList());
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

