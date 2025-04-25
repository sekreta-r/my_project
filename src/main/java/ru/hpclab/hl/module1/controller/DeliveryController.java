package ru.hpclab.hl.module1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.dto.DeliveryDTO;
import ru.hpclab.hl.module1.service.DeliveryService;


import lombok.RequiredArgsConstructor;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    private final ObservabilityService observabilityService;

    @GetMapping
    public List<DeliveryDTO> getAllDeliveries() {
        String metric = getClass().getSimpleName() + ":getAllDeliveries";
        observabilityService.start(metric);
        List<DeliveryDTO> result = deliveryService.getAllDeliveries();
        observabilityService.stop(metric);
        return result;
    }

    @GetMapping("/{id}")
    public DeliveryDTO getDeliveryById(@PathVariable Long id) {
        String metric = getClass().getSimpleName() + ":getDeliveryById";
        observabilityService.start(metric);
        DeliveryDTO result = deliveryService.getDeliveryById(id);
        observabilityService.stop(metric);
        return result;
    }

    @PostMapping
    public DeliveryDTO addDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        String metric = getClass().getSimpleName() + ":addDelivery";
        observabilityService.start(metric);
        DeliveryDTO result = deliveryService.saveDelivery(deliveryDTO);
        observabilityService.stop(metric);
        return result;
    }

    @PostMapping("/clear")
    public void clearDeliveries() {
        String metric = getClass().getSimpleName() + ":clearDeliveries";
        observabilityService.start(metric);
        deliveryService.deleteAllDeliveries();
        observabilityService.stop(metric);
    }

    @DeleteMapping("/{id}")
    public void deleteDelivery(@PathVariable Long id) {
        String metric = getClass().getSimpleName() + ":deleteDelivery";
        observabilityService.start(metric);
        deliveryService.deleteDelivery(id);
        observabilityService.stop(metric);
    }

    @GetMapping("/couriers/{courierId}/monthly-weight")
    public Map<Month, Double> getTotalWeightByMonth(@PathVariable Long courierId) {
        String metric = getClass().getSimpleName() + ":getTotalWeightByMonth";
        observabilityService.start(metric);
        Map<Month, Double> result = deliveryService.getTotalWeightByMonth(courierId);
        observabilityService.stop(metric);
        return result;
    }

    @GetMapping("/by-courier/{courierId}")
    public List<DeliveryDTO> getByCourier(@PathVariable Long courierId) {
        String metric = getClass().getSimpleName() + ":getByCourier";
        observabilityService.start(metric);
        List<DeliveryDTO> result = deliveryService.getAllDeliveries().stream()
                .filter(d -> d.getCourierId().equals(courierId))
                .collect(Collectors.toList());
        observabilityService.stop(metric);
        return result;
    }
}
