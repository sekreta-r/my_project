package ru.hpclab.hl.module1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.mapper.ParcelMapper;
import ru.hpclab.hl.module1.model.Parcel;
import ru.hpclab.hl.module1.service.ParcelService;


import lombok.RequiredArgsConstructor;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/parcels")
@RequiredArgsConstructor
public class ParcelController {

    private final ParcelService parcelService;

    // 🔽 [3] ДОБАВЛЕНО:
    private final ObservabilityService observabilityService;

    @GetMapping
    public List<Parcel> getAllParcels() {
        String metric = getClass().getSimpleName() + ":getAllParcels";
        observabilityService.start(metric);
        List<Parcel> result = parcelService.getAllParcels().stream()
                .map(ParcelMapper::entityToParcel)
                .collect(Collectors.toList());
        observabilityService.stop(metric);
        return result;
    }

    @GetMapping("/{id}")
    public Parcel getParcelById(@PathVariable Long id) {
        String metric = getClass().getSimpleName() + ":getParcelById";
        observabilityService.start(metric);
        Parcel result = ParcelMapper.entityToParcel(parcelService.getParcelById(id));
        observabilityService.stop(metric);
        return result;
    }

    @PostMapping
    public Parcel addParcel(@RequestBody Parcel parcel) {
        String metric = getClass().getSimpleName() + ":addParcel";
        observabilityService.start(metric);
        Parcel result = ParcelMapper.entityToParcel(parcelService.addParcel(ParcelMapper.parcelToEntity(parcel)));
        observabilityService.stop(metric);
        return result;
    }

    @PutMapping("/{id}")
    public Parcel updateParcel(@PathVariable Long id, @RequestBody Parcel parcel) {
        String metric = getClass().getSimpleName() + ":updateParcel";
        observabilityService.start(metric);
        Parcel result = ParcelMapper.entityToParcel(parcelService.updateParcel(id, ParcelMapper.parcelToEntity(parcel)));
        observabilityService.stop(metric);
        return result;
    }

    @DeleteMapping("/{id}")
    public void deleteParcel(@PathVariable Long id) {
        String metric = getClass().getSimpleName() + ":deleteParcel";
        observabilityService.start(metric);
        parcelService.deleteParcel(id);
        observabilityService.stop(metric);
    }

    @PostMapping("/clear")
    public void clearParcels() {
        String metric = getClass().getSimpleName() + ":clearParcels";
        observabilityService.start(metric);
        parcelService.deleteAllParcels();
        observabilityService.stop(metric);
    }
}
