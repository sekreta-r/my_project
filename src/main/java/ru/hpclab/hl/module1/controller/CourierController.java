package ru.hpclab.hl.module1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Courier;
import ru.hpclab.hl.module1.repository.CourierRepository;
import java.util.List;

@RestController
@RequestMapping("/couriers")
public class CourierController {

    private final CourierRepository courierRepository;

    public CourierController(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    @GetMapping
    public List<Courier> getAllCouriers() {
        return courierRepository.getAll();
    }

    @GetMapping("/{id}")
    public Courier getCourierById(@PathVariable Long id) {
        return courierRepository.findById(id);
    }

    @PostMapping
    public void addCourier(@RequestBody Courier courier) {
        courierRepository.add(courier);
    }

    @PutMapping("/{id}")
    public void updateCourier(@PathVariable Long id, @RequestBody Courier courier) {
        courierRepository.update(id, courier);
    }

    @DeleteMapping("/{id}")
    public void deleteCourier(@PathVariable Long id) {
        courierRepository.delete(id);
    }
}
