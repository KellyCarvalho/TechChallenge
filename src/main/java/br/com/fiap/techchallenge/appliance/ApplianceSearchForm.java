package br.com.fiap.techchallenge.appliance;

public record ApplianceSearchForm(String name, String brand, String model, Integer potencyInWatts, Voltage voltage, Long personId, Long addressId) {
}
