package ru.hpclab.hl.module1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Parcel;
import ru.hpclab.hl.module1.repository.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/parcels")
public class ParcelController {

    private final ParcelRepository parcelRepository;

    @Autowired
    public ParcelController(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    @GetMapping
    public List<Parcel> getAllParcels() {
        return parcelRepository.getAll();
    }

    @GetMapping("/{id}")
    public Parcel getParcelById(@PathVariable Long id) {
        return parcelRepository.findById(id);
    }


    @PostMapping
    public void addParcel(@RequestBody Parcel parcel) {
        parcelRepository.add(parcel);
    }

    @PutMapping("/{id}")
    public void updateParcel(@PathVariable Long id, @RequestBody Parcel parcel) {
        parcelRepository.update(id, parcel);
    }

    @DeleteMapping("/{id}")
    public void deleteParcel(@PathVariable Long id) {
        parcelRepository.delete(id);
    }
}
