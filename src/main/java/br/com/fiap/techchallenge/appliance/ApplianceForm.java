package br.com.fiap.techchallenge.appliance;

import jakarta.validation.constraints.NotBlank;

public record ApplianceForm(@NotBlank(message = "Nome é obrigatório")String name,
                            @NotBlank(message = "Marca é obrigatório")
                            String brand,
                            String model,
                            Integer potencyInWatts,
                            @NotBlank(message = "Voltagem é obrigatório")
                            String voltage) {

    public Appliance toEntity() {
        return new Appliance(name, brand, model, potencyInWatts, voltage);
    }


}
