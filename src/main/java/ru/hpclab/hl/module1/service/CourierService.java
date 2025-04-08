package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.entity.CourierEntity;
import ru.hpclab.hl.module1.repository.CourierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourierService {

    private final CourierRepository courierRepository;

    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    public List<CourierEntity> getAllCouriers() {
        return courierRepository.findAll();
    }

    public CourierEntity getCourierById(Long id) {
        Optional<CourierEntity> courierEntity = courierRepository.findById(id);
        return courierEntity.orElse(null);
    }

    public CourierEntity saveCourier(CourierEntity courierEntity) {
        return courierRepository.save(courierEntity);
    }

    public CourierEntity updateCourier(Long id, CourierEntity newCourierEntity) {
        if (courierRepository.existsById(id)) {
            newCourierEntity.setId(id);
            return courierRepository.save(newCourierEntity);
        }
        return null; // или выбросить исключение, если не найден
    }


    public void deleteCourier(Long id) {
        courierRepository.deleteById(id);
    }

    public void deleteAllCouriers() {
        courierRepository.deleteAll();
    }
}

