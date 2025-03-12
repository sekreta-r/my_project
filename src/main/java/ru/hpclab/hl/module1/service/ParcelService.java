package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.model.Parcel;
import ru.hpclab.hl.module1.repository.ParcelRepository;

import java.util.List;

@Service
public class ParcelService {

    private final ParcelRepository parcelRepository;

    public ParcelService(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    public Parcel addParcel(Parcel parcel) {
        parcelRepository.add(parcel);
        return parcel;
    }

    public List<Parcel> getAllParcels() {
        return parcelRepository.getAll();
    }

    public Parcel findById(Long id) {
        return parcelRepository.findById(id);
    }

    public void updateParcel(Long id, Parcel parcel) {
        parcelRepository.update(id, parcel);
    }

    public void deleteParcel(Long id) {
        parcelRepository.delete(id);
    }
}
