package br.com.fiap.techchallenge.appliance;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Appliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private String model;
    private Integer potencyInWatts;
    private Voltage voltage;

    public Appliance() {
    }

    public Appliance(String name, String brand, String model, Integer potencyInWatts, Voltage voltage) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.potencyInWatts = potencyInWatts;
        this.voltage = voltage;
    }

    public Appliance(String name, String brand, String model, Integer potencyInWatts, String voltage) {
       this(name, brand, model, potencyInWatts, Voltage.get(voltage));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPotencyInWatts() {
        return potencyInWatts;
    }

    public void setPotencyInWatts(Integer potencyInWatts) {
        this.potencyInWatts = potencyInWatts;
    }

    public Voltage getVoltage() {
        return voltage;
    }

    public void setVoltage(Voltage voltage) {
        this.voltage = voltage;
    }

}
