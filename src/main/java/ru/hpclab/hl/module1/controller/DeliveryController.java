package ru.hpclab.hl.module1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.dto.DeliveryDTO;
import ru.hpclab.hl.module1.service.DeliveryService;

import java.time.Month;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public List<DeliveryDTO> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/{id}")
    public DeliveryDTO getDeliveryById(@PathVariable Long id) {
        return deliveryService.getDeliveryById(id);
    }

    @PostMapping
    public DeliveryDTO addDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        return deliveryService.saveDelivery(deliveryDTO);
    }

    @PostMapping("/clear")
    public void clearDeliveries() {
        deliveryService.deleteAllDeliveries();
    }

    @DeleteMapping("/{id}")
    public void deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
    }

    // Новый метод: получение веса доставленных грузов по месяцам для курьера
    @GetMapping("/couriers/{courierId}/monthly-weight")
    public Map<Month, Double> getTotalWeightByMonth(@PathVariable Long courierId) {
        return deliveryService.getTotalWeightByMonth(courierId);
    }
}
