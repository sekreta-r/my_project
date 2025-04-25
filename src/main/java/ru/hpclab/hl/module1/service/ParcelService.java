package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.entity.ParcelEntity;
import ru.hpclab.hl.module1.repository.ParcelRepository;

// 🔽 [1] ДОБАВЛЕНО:
import lombok.RequiredArgsConstructor;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParcelService {

    private final ParcelRepository parcelRepository;

    // 🔽 [3] ДОБАВЛЕНО:
    private final ObservabilityService observabilityService;

    public List<ParcelEntity> getAllParcels() {
        String metric = getClass().getSimpleName() + ":getAllParcels";
        observabilityService.start(metric);
        List<ParcelEntity> result = parcelRepository.findAll();
        observabilityService.stop(metric);
        return result;
    }

    public ParcelEntity getParcelById(Long id) {
        String metric = getClass().getSimpleName() + ":getParcelById";
        observabilityService.start(metric);
        ParcelEntity result = parcelRepository.findById(id).orElse(null);
        observabilityService.stop(metric);
        return result;
    }

    public ParcelEntity addParcel(ParcelEntity parcel) {
        String metric = getClass().getSimpleName() + ":addParcel";
        observabilityService.start(metric);
        ParcelEntity result = parcelRepository.save(parcel);
        observabilityService.stop(metric); // 🔽 [9]
        return result;
    }

    public ParcelEntity updateParcel(Long id, ParcelEntity updatedParcel) {
        String metric = getClass().getSimpleName() + ":updateParcel"; // 🔽 [10]
        observabilityService.start(metric);
        ParcelEntity result = parcelRepository.findById(id).map(parcel -> {
            parcel.setWeight(updatedParcel.getWeight());
            parcel.setDimensions(updatedParcel.getDimensions());
            parcel.setDestinationAddress(updatedParcel.getDestinationAddress());
            return parcelRepository.save(parcel);
        }).orElse(null);
        observabilityService.stop(metric); // 🔽 [11]
        return result;
    }

    public void deleteParcel(Long id) {
        String metric = getClass().getSimpleName() + ":deleteParcel"; // 🔽 [12]
        observabilityService.start(metric);
        parcelRepository.deleteById(id);
        observabilityService.stop(metric);
    }

    public void deleteAllParcels() {
        String metric = getClass().getSimpleName() + ":deleteAllParcels";
        observabilityService.start(metric);
        parcelRepository.deleteAll();
        observabilityService.stop(metric);
    }
}
