package ru.hpclab.hl.module1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.dto.CourierDTO;
import ru.hpclab.hl.module1.mapper.CourierMapper;
import ru.hpclab.hl.module1.service.CourierService;

import lombok.RequiredArgsConstructor;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/couriers")
@RequiredArgsConstructor
public class CourierController {

    private final CourierService courierService;

    private final ObservabilityService observabilityService;

    @GetMapping
    public List<CourierDTO> getAllCouriers() {
        String metric = getClass().getSimpleName() + ":getAllCouriers";
        observabilityService.start(metric);
        List<CourierDTO> result = courierService.getAllCouriers().stream()
                .map(CourierMapper::toDTO)
                .collect(Collectors.toList());
        observabilityService.stop(metric);
        return result;
    }

    @GetMapping("/{id}")
    public CourierDTO getCourierById(@PathVariable Long id) {
        String metric = getClass().getSimpleName() + ":getCourierById";
        observabilityService.start(metric);
        CourierDTO result = CourierMapper.toDTO(courierService.getCourierById(id));
        observabilityService.stop(metric);
        return result;
    }

    @PostMapping
    public CourierDTO addCourier(@RequestBody CourierDTO courierDTO) {
        String metric = getClass().getSimpleName() + ":addCourier";
        observabilityService.start(metric);
        CourierDTO result = CourierMapper.toDTO(courierService.saveCourier(CourierMapper.toEntity(courierDTO)));
        observabilityService.stop(metric);
        return result;
    }

    @PostMapping("/clear")
    public void clearCouriers() {
        String metric = getClass().getSimpleName() + ":clearCouriers";
        observabilityService.start(metric);
        courierService.deleteAllCouriers();
        observabilityService.stop(metric);
    }

    @PutMapping("/{id}")
    public CourierDTO updateCourier(@PathVariable Long id, @RequestBody CourierDTO courierDTO) {
        String metric = getClass().getSimpleName() + ":updateCourier";
        observabilityService.start(metric);
        CourierDTO result = CourierMapper.toDTO(courierService.updateCourier(id, CourierMapper.toEntity(courierDTO)));
        observabilityService.stop(metric);
        return result;
    }

    @DeleteMapping("/{id}")
    public void deleteCourier(@PathVariable Long id) {
        String metric = getClass().getSimpleName() + ":deleteCourier";
        observabilityService.start(metric);
        courierService.deleteCourier(id);
        observabilityService.stop(metric);
    }
}
