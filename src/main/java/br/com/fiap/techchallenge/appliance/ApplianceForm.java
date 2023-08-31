package br.com.fiap.techchallenge.appliance;

import br.com.fiap.techchallenge.address.Address;
import br.com.fiap.techchallenge.person.Person;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ApplianceForm(@NotBlank String name,
                            @NotBlank String brand,
                            String model,
                            Integer potencyInWatts,
                            @NotNull Voltage voltage,
                            Long personId,
                            @NotNull Long addressId) {

    public Appliance toEntity(Person person, Address address) {
        return new Appliance(name, brand, model, potencyInWatts, voltage, person, address);
    }

}
