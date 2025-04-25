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


import lombok.RequiredArgsConstructor;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final ParcelRepository parcelRepository;
    private final CourierRepository courierRepository;


    private final ObservabilityService observabilityService;

    public List<DeliveryDTO> getAllDeliveries() {
        String metric = getClass().getSimpleName() + ":getAllDeliveries";
        observabilityService.start(metric);
        List<DeliveryDTO> result = deliveryRepository.findAll().stream()
                .map(DeliveryMapper::toDTO)
                .collect(Collectors.toList());
        observabilityService.stop(metric);
        return result;
    }

    public DeliveryDTO getDeliveryById(Long id) {
        String metric = getClass().getSimpleName() + ":getDeliveryById";
        observabilityService.start(metric);
        Optional<DeliveryEntity> deliveryEntity = deliveryRepository.findById(id);
        DeliveryDTO result = deliveryEntity.map(DeliveryMapper::toDTO).orElse(null);
        observabilityService.stop(metric);
        return result;
    }

    public DeliveryDTO saveDelivery(DeliveryDTO deliveryDTO) {
        String metric = getClass().getSimpleName() + ":saveDelivery";
        observabilityService.start(metric);
        ParcelEntity parcel = parcelRepository.findById(deliveryDTO.getParcelId())
                .orElseThrow(() -> new RuntimeException("Посылка не найдена"));

        CourierEntity courier = courierRepository.findById(deliveryDTO.getCourierId())
                .orElseThrow(() -> new RuntimeException("Курьер не найден"));

        DeliveryEntity deliveryEntity = DeliveryMapper.toEntity(deliveryDTO, parcel, courier);
        DeliveryDTO result = DeliveryMapper.toDTO(deliveryRepository.save(deliveryEntity));
        observabilityService.stop(metric);
        return result;
    }

    public void deleteDelivery(Long id) {
        String metric = getClass().getSimpleName() + ":deleteDelivery";
        observabilityService.start(metric);
        deliveryRepository.deleteById(id);
        observabilityService.stop(metric);
    }

    public Map<Month, Double> getTotalWeightByMonth(Long courierId) {
        String metric = getClass().getSimpleName() + ":getTotalWeightByMonth";
        observabilityService.start(metric);
        List<Object[]> results = deliveryRepository.getTotalWeightByMonth(courierId, DeliveryStatus.DELIVERED);

        Map<Month, Double> result = results.stream()
                .collect(Collectors.toMap(
                        row -> Month.of(((Number) row[0]).intValue()),
                        row -> ((Number) row[1]).doubleValue()
                ));
        observabilityService.stop(metric);
        return result;
    }

    public void deleteAllDeliveries() {
        String metric = getClass().getSimpleName() + ":deleteAllDeliveries";
        observabilityService.start(metric);
        deliveryRepository.deleteAll();
        observabilityService.stop(metric);
    }
}
