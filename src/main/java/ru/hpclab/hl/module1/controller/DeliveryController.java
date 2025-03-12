package ru.hpclab.hl.module1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Delivery;
import ru.hpclab.hl.module1.repository.DeliveryRepository;
import ru.hpclab.hl.module1.service.DeliveryService;
import java.util.List;
import java.time.Month;
import java.util.Map;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryService deliveryService;


    public DeliveryController(DeliveryRepository deliveryRepository, DeliveryService deliveryService) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryService = deliveryService;
    }

    @GetMapping("/couriers/{courierId}/monthly-weight")
    public Map<Month, Double> getTotalWeightByMonth(@PathVariable Long courierId) {
        return deliveryService.getTotalWeightByMonth(courierId);
    }

    @GetMapping
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.getAll();
    }

    @GetMapping("/{id}")
    public Delivery getDeliveryById(@PathVariable Long id) {
        return deliveryRepository.findById(id);
    }

    @PostMapping
    public void addDelivery(@RequestBody Delivery delivery) {
        deliveryRepository.add(delivery);
    }

    @PutMapping("/{id}")
    public void updateDelivery(@PathVariable Long id, @RequestBody Delivery delivery) {
        deliveryRepository.update(id, delivery);
    }

    @DeleteMapping("/{id}")
    public void deleteDelivery(@PathVariable Long id) {
        deliveryRepository.delete(id);
    }
}
