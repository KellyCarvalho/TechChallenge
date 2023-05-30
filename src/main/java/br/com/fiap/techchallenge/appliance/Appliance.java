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

    public Long getId() {
        return id;
    }

    public Appliance setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getPotencyInWatts() {
        return potencyInWatts;
    }

    public Voltage getVoltage() {
        return voltage;
    }

    public enum Voltage {
        ONE_HUNDRED_TWENTY_SEVEN("127V"),
        TWO_HUNDRED_AND_TWENTY("220V"),
        BIVOLT("bivolt");

        private String displayName;

        Voltage(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

}
