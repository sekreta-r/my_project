package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.dto.DeliveryDTO;
import ru.hpclab.hl.module1.entity.DeliveryEntity;
import ru.hpclab.hl.module1.entity.CourierEntity;
import ru.hpclab.hl.module1.entity.ParcelEntity;
import ru.hpclab.hl.module1.mapper.DeliveryMapper;
import ru.hpclab.hl.module1.repository.DeliveryRepository;
import ru.hpclab.hl.module1.repository.CourierRepository;
import ru.hpclab.hl.module1.repository.ParcelRepository;
import ru.hpclab.hl.module1.entity.DeliveryStatus;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final ParcelRepository parcelRepository;
    private final CourierRepository courierRepository;

    public DeliveryService(DeliveryRepository deliveryRepository, ParcelRepository parcelRepository, CourierRepository courierRepository) {
        this.deliveryRepository = deliveryRepository;
        this.parcelRepository = parcelRepository;
        this.courierRepository = courierRepository;
    }

    public List<DeliveryDTO> getAllDeliveries() {
        return deliveryRepository.findAll().stream()
                .map(DeliveryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DeliveryDTO getDeliveryById(Long id) {
        Optional<DeliveryEntity> deliveryEntity = deliveryRepository.findById(id);
        return deliveryEntity.map(DeliveryMapper::toDTO).orElse(null);
    }

    public DeliveryDTO saveDelivery(DeliveryDTO deliveryDTO) {
        ParcelEntity parcel = parcelRepository.findById(deliveryDTO.getParcelId())
                .orElseThrow(() -> new RuntimeException("Посылка не найдена"));

        CourierEntity courier = courierRepository.findById(deliveryDTO.getCourierId())
                .orElseThrow(() -> new RuntimeException("Курьер не найден"));

        DeliveryEntity deliveryEntity = DeliveryMapper.toEntity(deliveryDTO, parcel, courier);
        return DeliveryMapper.toDTO(deliveryRepository.save(deliveryEntity));
    }

    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }

    public Map<Month, Double> getTotalWeightByMonth(Long courierId) {
        List<Object[]> results = deliveryRepository.getTotalWeightByMonth(courierId, DeliveryStatus.DELIVERED);

        return results.stream()
                .collect(Collectors.toMap(
                        row -> Month.of(((Number) row[0]).intValue()),
                        row -> ((Number) row[1]).doubleValue()
                ));
    }

    public void deleteAllDeliveries() {
        deliveryRepository.deleteAll();
    }


}
