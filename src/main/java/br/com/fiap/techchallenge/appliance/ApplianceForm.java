package br.com.fiap.techchallenge.appliance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public record ApplianceForm(@NotBlank String name,
                            @NotBlank String brand,
                            String model,
                            Integer potencyInWatts,
                            @NotNull Voltage voltage) {

    public Appliance toEntity() {
        return new Appliance(name, brand, model, potencyInWatts, voltage);
    }

}
