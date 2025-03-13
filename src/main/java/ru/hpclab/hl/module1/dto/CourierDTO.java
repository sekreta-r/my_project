package ru.hpclab.hl.module1.dto;

public class CourierDTO {
    private Long id;
    private String fullName;
    private String transport;
    private String workZone;

    public CourierDTO() {
    }

    public CourierDTO(Long id, String fullName, String transport, String workZone) {
        this.id = id;
        this.fullName = fullName;
        this.transport = transport;
        this.workZone = workZone;
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
}