package ru.hpclab.hl.module1.repository;

import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.model.Delivery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DeliveryRepository {

    private final List<Delivery> deliveries = new ArrayList<>();

    public void add(Delivery delivery) {
        deliveries.add(delivery);
    }

    public List<Delivery> getAll() {
        return deliveries;
    }

    public Delivery findById(Long id) {
        return deliveries.stream()
                .filter(delivery -> delivery.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void update(Long id, Delivery newDelivery) {
        for (int i = 0; i < deliveries.size(); i++) {
            if (deliveries.get(i).getId().equals(id)) {
                deliveries.set(i, newDelivery);
                return;
            }
        }
    }

    public void delete(Long id) {
        deliveries.removeIf(delivery -> delivery.getId().equals(id));
    }
}
