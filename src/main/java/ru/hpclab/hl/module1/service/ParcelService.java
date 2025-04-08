package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.entity.ParcelEntity;
import ru.hpclab.hl.module1.model.Parcel;
import ru.hpclab.hl.module1.repository.ParcelRepository;
import ru.hpclab.hl.module1.mapper.ParcelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParcelService {
    private final ParcelRepository parcelRepository;

    public ParcelService(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    public List<ParcelEntity> getAllParcels() {
        return parcelRepository.findAll();
    }

    public ParcelEntity getParcelById(Long id) {
        return parcelRepository.findById(id).orElse(null);
    }

    public ParcelEntity addParcel(ParcelEntity parcel) {
        return parcelRepository.save(parcel);
    }

    public ParcelEntity updateParcel(Long id, ParcelEntity updatedParcel) {
        return parcelRepository.findById(id).map(parcel -> {
            parcel.setWeight(updatedParcel.getWeight());
            parcel.setDimensions(updatedParcel.getDimensions());
            parcel.setDestinationAddress(updatedParcel.getDestinationAddress());
            return parcelRepository.save(parcel);
        }).orElse(null);
    }

    public void deleteParcel(Long id) {
        parcelRepository.deleteById(id);
    }

    public void deleteAllParcels() {
        parcelRepository.deleteAll();
    }

}
