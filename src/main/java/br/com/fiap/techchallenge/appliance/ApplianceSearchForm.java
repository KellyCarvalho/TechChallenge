package br.com.fiap.techchallenge.appliance;

import br.com.fiap.techchallenge.address.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ApplianceSearchForm(String name, String brand, String model, Integer potencyInWatts) {
}
