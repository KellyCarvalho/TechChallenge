package br.com.fiap.techchallenge.appliance;

public record ApplianceView(String name,
                            String brand,
                            String model,
                            Integer potencyInWatts,
                            String voltage) {

    public ApplianceView(Appliance appliance) {
        this(appliance.getName(), appliance.getBrand(), appliance.getModel(), appliance.getPotencyInWatts(), appliance.getVoltage().getDisplayName());
    }
}
