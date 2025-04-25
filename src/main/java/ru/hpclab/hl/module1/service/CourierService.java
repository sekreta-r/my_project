package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.entity.CourierEntity;
import ru.hpclab.hl.module1.repository.CourierRepository;


import lombok.RequiredArgsConstructor;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourierService {

    private final CourierRepository courierRepository;


    private final ObservabilityService observabilityService;

    public List<CourierEntity> getAllCouriers() {
        String metric = getClass().getSimpleName() + ":getAllCouriers";
        observabilityService.start(metric);
        List<CourierEntity> result = courierRepository.findAll();
        observabilityService.stop(metric);
        return result;
    }

    public CourierEntity getCourierById(Long id) {
        String metric = getClass().getSimpleName() + ":getCourierById";
        observabilityService.start(metric);
        Optional<CourierEntity> courierEntity = courierRepository.findById(id);
        observabilityService.stop(metric);
        return courierEntity.orElse(null);
    }

    public CourierEntity saveCourier(CourierEntity courierEntity) {
        String metric = getClass().getSimpleName() + ":saveCourier";
        observabilityService.start(metric);
        CourierEntity result = courierRepository.save(courierEntity);
        observabilityService.stop(metric);
        return result;
    }

    public CourierEntity updateCourier(Long id, CourierEntity newCourierEntity) {
        String metric = getClass().getSimpleName() + ":updateCourier";
        observabilityService.start(metric);
        if (courierRepository.existsById(id)) {
            newCourierEntity.setId(id);
            CourierEntity result = courierRepository.save(newCourierEntity);
            observabilityService.stop(metric);
            return result;
        }
        observabilityService.stop(metric);
        return null;
    }

    public void deleteCourier(Long id) {
        String metric = getClass().getSimpleName() + ":deleteCourier";
        observabilityService.start(metric);
        courierRepository.deleteById(id);
        observabilityService.stop(metric);
    }

    public void deleteAllCouriers() {
        String metric = getClass().getSimpleName() + ":deleteAllCouriers";
        observabilityService.start(metric);
        courierRepository.deleteAll();
        observabilityService.stop(metric);
    }
}
