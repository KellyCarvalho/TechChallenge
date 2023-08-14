package br.com.fiap.techchallenge.appliance;

import br.com.fiap.techchallenge.address.AddressView;

public record ApplianceView(Long id, String name, String brand, String model, Integer potencyInWatts, String voltage, AddressView address) {

    public ApplianceView(Appliance appliance) {
        this(appliance.getId(), appliance.getName(), appliance.getBrand(), appliance.getModel(), appliance.getPotencyInWatts(), appliance.getVoltage().name(), new AddressView(appliance.getAddress()));
    }
}
