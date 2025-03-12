package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.Delivery;
import ru.hpclab.hl.module1.repository.DeliveryRepository;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public Map<Month, Double> getTotalWeightByMonth(Long courierId) {
        List<Delivery> deliveries = deliveryRepository.getAll().stream()
                .filter(delivery -> delivery.getCourier().getId().equals(courierId) &&
                        "Delivered".equalsIgnoreCase(delivery.getStatus()))
                .toList();

        return deliveries.stream()
                .collect(Collectors.groupingBy(
                        d -> d.getDeliveryDate().getMonth(),
                        Collectors.summingDouble(d -> d.getParcel().getWeight())
                ));
    }
}
