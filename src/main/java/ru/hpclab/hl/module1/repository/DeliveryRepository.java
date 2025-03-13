package ru.hpclab.hl.module1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.entity.DeliveryEntity;
import ru.hpclab.hl.module1.entity.DeliveryStatus;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Map;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {

    @Query("SELECT MONTH(d.deliveryDate) AS month, SUM(p.weight) AS totalWeight " +
            "FROM DeliveryEntity d " +
            "JOIN d.parcel p " +
            "WHERE d.courier.id = :courierId AND d.status = :status " +
            "GROUP BY MONTH(d.deliveryDate)")
    List<Object[]> getTotalWeightByMonth(@Param("courierId") Long courierId, @Param("status") DeliveryStatus status);


}
