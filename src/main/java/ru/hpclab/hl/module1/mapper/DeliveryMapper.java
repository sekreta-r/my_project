package ru.hpclab.hl.module1.mapper;

import ru.hpclab.hl.module1.dto.DeliveryDTO;
import ru.hpclab.hl.module1.entity.DeliveryEntity;
import ru.hpclab.hl.module1.entity.CourierEntity;
import ru.hpclab.hl.module1.entity.ParcelEntity;
import ru.hpclab.hl.module1.entity.DeliveryStatus;

public class DeliveryMapper {
    private DeliveryMapper() {
    }

    public static DeliveryEntity toEntity(DeliveryDTO dto, ParcelEntity parcel, CourierEntity courier) {
        if (dto == null) return null;

        return new DeliveryEntity(
                dto.getId(),
                parcel,
                courier,
                dto.getDeliveryDate(),
                dto.getStatus()
        );
    }

    public static DeliveryDTO toDTO(DeliveryEntity entity) {
        if (entity == null) return null;

        return new DeliveryDTO(
                entity.getId(),
                entity.getParcel().getId(),
                entity.getCourier().getId(),
                entity.getDeliveryDate(),
                entity.getStatus()
        );
    }
}
