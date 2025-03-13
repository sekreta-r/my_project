package ru.hpclab.hl.module1.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_delivery")
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "parcel_id", nullable = false)
    private ParcelEntity parcel;

    @ManyToOne
    @JoinColumn(name = "courier_id", nullable = false)
    private CourierEntity courier;

    private LocalDate deliveryDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;

    public DeliveryEntity() {
    }

    public DeliveryEntity(Long id, ParcelEntity parcel, CourierEntity courier, LocalDate deliveryDate, DeliveryStatus status) {
        this.id = id;
        this.parcel = parcel;
        this.courier = courier;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParcelEntity getParcel() {
        return parcel;
    }

    public void setParcel(ParcelEntity parcel) {
        this.parcel = parcel;
    }

    public CourierEntity getCourier() {
        return courier;
    }

    public void setCourier(CourierEntity courier) {
        this.courier = courier;
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
