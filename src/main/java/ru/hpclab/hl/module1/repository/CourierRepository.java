package ru.hpclab.hl.module1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hpclab.hl.module1.entity.CourierEntity;

public interface CourierRepository extends JpaRepository<CourierEntity, Long> {
}
