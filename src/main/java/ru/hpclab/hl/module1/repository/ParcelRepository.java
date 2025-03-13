package ru.hpclab.hl.module1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hpclab.hl.module1.entity.ParcelEntity;


public interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {

}


