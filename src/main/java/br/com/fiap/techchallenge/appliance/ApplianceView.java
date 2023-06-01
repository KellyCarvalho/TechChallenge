package br.com.fiap.techchallenge.appliance;

public record ApplianceView(Long id, String name, String brand, String model, Integer potencyInWatts, String voltage) {

    public ApplianceView(Appliance appliance) {
        this(appliance.getId(), appliance.getName(), appliance.getBrand(), appliance.getModel(), appliance.getPotencyInWatts(), appliance.getVoltage().name());
    }
}
