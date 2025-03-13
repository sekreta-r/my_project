package ru.hpclab.hl.module1.model;

import java.time.LocalDate;

public class Delivery {
    private Long id;
    private Parcel parcel;
    private Courier courier;
    private LocalDate deliveryDate;
    private String status;

    // Конструктор по умолчанию
    public Delivery() {}

    // Конструктор с параметрами
    public Delivery(Long id, Parcel parcel, Courier courier, LocalDate deliveryDate, String status) {
        this.id = id;
        this.parcel = parcel;
        this.courier = courier;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Parcel getParcel() { return parcel; }
    public void setParcel(Parcel parcel) { this.parcel = parcel; }

    public Courier getCourier() { return courier; }
    public void setCourier(Courier courier) { this.courier = courier; }

    public LocalDate getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", parcel=" + parcel +
                ", courier=" + courier +
                ", deliveryDate=" + deliveryDate +
                ", status='" + status + '\'' +
                '}';
    }
}
