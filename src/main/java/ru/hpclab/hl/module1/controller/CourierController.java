package ru.hpclab.hl.module1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.dto.CourierDTO;
import ru.hpclab.hl.module1.mapper.CourierMapper;
import ru.hpclab.hl.module1.service.CourierService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/couriers")
public class CourierController {

    private final CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @GetMapping
    public List<CourierDTO> getAllCouriers() {
        return courierService.getAllCouriers().stream()
                .map(CourierMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CourierDTO getCourierById(@PathVariable Long id) {
        return CourierMapper.toDTO(courierService.getCourierById(id));
    }

    @PostMapping
    public CourierDTO addCourier(@RequestBody CourierDTO courierDTO) {
        return CourierMapper.toDTO(courierService.saveCourier(CourierMapper.toEntity(courierDTO)));
    }

    @PostMapping("/clear")
    public void clearCouriers() {
        courierService.deleteAllCouriers();
    }

    @PutMapping("/{id}")
    public CourierDTO updateCourier(@PathVariable Long id, @RequestBody CourierDTO courierDTO) {
        return CourierMapper.toDTO(courierService.updateCourier(id, CourierMapper.toEntity(courierDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteCourier(@PathVariable Long id) {
        courierService.deleteCourier(id);
    }


}
