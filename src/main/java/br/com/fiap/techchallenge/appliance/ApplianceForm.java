package br.com.fiap.techchallenge.appliance;

public record ApplianceForm(String name,
                            String brand,
                            String model,
                            Integer potencyInWatts,
                            String voltage) {

    public Appliance toEntity() {
        return new Appliance(name, brand, model, potencyInWatts, voltage);
    }


}
