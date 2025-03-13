package ru.hpclab.hl.module1.dto;

import java.time.LocalDate;
import ru.hpclab.hl.module1.entity.DeliveryStatus;

public class DeliveryDTO {
    private Long id;
    private Long parcelId;
    private Long courierId;
    private LocalDate deliveryDate;
    private DeliveryStatus status;

    public DeliveryDTO() {
    }

    public DeliveryDTO(Long id, Long parcelId, Long courierId, LocalDate deliveryDate, DeliveryStatus status) {
        this.id = id;
        this.parcelId = parcelId;
        this.courierId = courierId;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

}
