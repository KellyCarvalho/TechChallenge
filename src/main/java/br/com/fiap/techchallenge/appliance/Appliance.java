package br.com.fiap.techchallenge.appliance;

import br.com.fiap.techchallenge.address.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Appliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 1, max = 255)
    private String name;
    @NotBlank
    @Size(min = 1, max = 100)
    private String brand;
    private String model;
    private Integer potencyInWatts;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Voltage voltage;

    @ManyToOne
    private Address address;

    @Deprecated
    public Appliance() {
    }

    public Appliance(String name, String brand, String model, Integer potencyInWatts, Voltage voltage, Address address) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.potencyInWatts = potencyInWatts;
        this.voltage = voltage;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }
}
