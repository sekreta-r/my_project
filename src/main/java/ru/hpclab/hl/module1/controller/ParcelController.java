package ru.hpclab.hl.module1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.mapper.ParcelMapper;
import ru.hpclab.hl.module1.model.Parcel;
import ru.hpclab.hl.module1.service.ParcelService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/parcels")
public class ParcelController {
    private final ParcelService parcelService;

    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @GetMapping
    public List<Parcel> getAllParcels() {
        return parcelService.getAllParcels().stream()
                .map(ParcelMapper::entityToParcel) // Преобразуем Entity в модель
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Parcel getParcelById(@PathVariable Long id) {
        return ParcelMapper.entityToParcel(parcelService.getParcelById(id));
    }

    @PostMapping
    public Parcel addParcel(@RequestBody Parcel parcel) {
        return ParcelMapper.entityToParcel(parcelService.addParcel(ParcelMapper.parcelToEntity(parcel)));
    }

    @PutMapping("/{id}")
    public Parcel updateParcel(@PathVariable Long id, @RequestBody Parcel parcel) {
        return ParcelMapper.entityToParcel(parcelService.updateParcel(id, ParcelMapper.parcelToEntity(parcel)));
    }

    @DeleteMapping("/{id}")
    public void deleteParcel(@PathVariable Long id) {
        parcelService.deleteParcel(id);
    }

    @PostMapping("/clear")
    public void clearParcels() {
        parcelService.deleteAllParcels();
    }
}
