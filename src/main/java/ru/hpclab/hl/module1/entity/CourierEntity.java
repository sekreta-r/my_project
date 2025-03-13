package ru.hpclab.hl.module1.entity;

import jakarta.persistence.*;
import java.util.List;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_courier")
public class CourierEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String fullName;
    private String transport;
    private String workZone;

    @OneToMany(mappedBy = "courier", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DeliveryEntity> deliveries;

    public CourierEntity() {
    }

    public CourierEntity(Long id, String fullName, String transport, String workZone, List<DeliveryEntity> deliveries) {
        this.id = id;
        this.fullName = fullName;
        this.transport = transport;
        this.workZone = workZone;
        this.deliveries = deliveries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getWorkZone() {
        return workZone;
    }

    public void setWorkZone(String workZone) {
        this.workZone = workZone;
    }

    public List<DeliveryEntity> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryEntity> deliveries) {
        this.deliveries = deliveries;
    }

    @Override
    public String toString() {
        return "CourierEntity{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", transport='" + transport + '\'' +
                ", workZone='" + workZone + '\'' +
                ", deliveries=" + deliveries +
                '}';
    }
}
