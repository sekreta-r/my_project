package ru.hpclab.hl.module1.mapper;

import ru.hpclab.hl.module1.dto.CourierDTO;
import ru.hpclab.hl.module1.entity.CourierEntity;

public class CourierMapper {
    private CourierMapper() {
    }

    /**
     * Конвертация из DTO в Entity
     */
    public static CourierEntity toEntity(CourierDTO dto) {
        if (dto == null) return null;

        return new CourierEntity(
                dto.getId(),
                dto.getFullName(),
                dto.getTransport(),
                dto.getWorkZone(),
                null // Удалили поле deliveries, так как оно больше не передается в DTO
        );
    }

    /**
     * Конвертация из Entity в DTO
     */
    public static CourierDTO toDTO(CourierEntity entity) {
        if (entity == null) return null;

        return new CourierDTO(
                entity.getId(),
                entity.getFullName(),
                entity.getTransport(),
                entity.getWorkZone()
                // Удалили поле deliveries, так как его больше нет в DTO
        );
    }
}
