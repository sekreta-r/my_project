package ru.hpclab.hl.module1.repository;

import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.model.Courier;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourierRepository {

    private final List<Courier> couriers = new ArrayList<>();

    public void add(Courier courier) {
        couriers.add(courier);
    }

    public List<Courier> getAll() {
        return couriers;
    }

    public Courier findById(Long id) {
        return couriers.stream()
                .filter(courier -> courier.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void update(Long id, Courier newCourier) {
        for (int i = 0; i < couriers.size(); i++) {
            if (couriers.get(i).getId().equals(id)) {
                couriers.set(i, newCourier);
                return;
            }
        }
    }

    public void delete(Long id) {
        couriers.removeIf(courier -> courier.getId().equals(id));
    }
}
