package ru.hpclab.hl.module1.repository;

import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.model.Parcel;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ParcelRepository {

    private final List<Parcel> parcels = new ArrayList<>();

    public Parcel add(Parcel parcel) {
        parcels.add(parcel);
        return parcel;
    }


    public List<Parcel> getAll() {
        return parcels;
    }

    public Parcel findById(Long id) {
        return parcels.stream()
                .filter(parcel -> parcel.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void update(Long id, Parcel newParcel) {
        for (int i = 0; i < parcels.size(); i++) {
            if (parcels.get(i).getId().equals(id)) {
                parcels.set(i, newParcel);
                return;
            }
        }
    }

    public void delete(Long id) {
        parcels.removeIf(parcel -> parcel.getId().equals(id));
    }

    public void clear() {
        parcels.clear(); // Очистка списка посылок
    }
}
