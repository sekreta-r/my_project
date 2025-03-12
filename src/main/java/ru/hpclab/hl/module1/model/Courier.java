package ru.hpclab.hl.module1.model;

public class Courier {
    private Long id;
    private String fullName;
    private String transport;
    private String workZone;

    public Courier(Long id, String fullName, String transport, String workZone) {
        this.id = id;
        this.fullName = fullName;
        this.transport = transport;
        this.workZone = workZone;
    }

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getTransport() { return transport; }
    public String getWorkZone() { return workZone; }

    public void setId(Long id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setTransport(String transport) { this.transport = transport; }
    public void setWorkZone(String workZone) { this.workZone = workZone; }
}
