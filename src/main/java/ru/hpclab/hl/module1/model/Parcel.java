package ru.hpclab.hl.module1.model;

public class Parcel {
    private Long id;
    private double weight;
    private String dimensions;
    private String destinationAddress;

    public Parcel(Long id, double weight, String dimensions, String destinationAddress) {
        this.id = id;
        this.weight = weight;
        this.dimensions = dimensions;
        this.destinationAddress = destinationAddress;
    }

    public Long getId() { return id; }
    public double getWeight() { return weight; }
    public String getDimensions() { return dimensions; }
    public String getDestinationAddress() { return destinationAddress; }

    public void setId(Long id) { this.id = id; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setDimensions(String dimensions) { this.dimensions = dimensions; }
    public void setDestinationAddress(String destinationAddress) { this.destinationAddress = destinationAddress; }
}
