package br.com.fiap.techchallenge.appliance;

import br.com.fiap.techchallenge.address.AddressView;
import br.com.fiap.techchallenge.person.PersonView;

public record ApplianceView(Long id, String name, String brand, String model, Integer potencyInWatts, String voltage, PersonView person, AddressView address) {

    public ApplianceView(Appliance appliance) {
        this(appliance.getId(), appliance.getName(), appliance.getBrand(), appliance.getModel(), appliance.getPotencyInWatts(), appliance.getVoltage().name(), appliance.getPerson().map(PersonView::new).orElse(null), new AddressView(appliance.getAddress()));
    }
}
