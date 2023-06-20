package br.com.fiap.techchallenge.address;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddressForm(@NotBlank(message = "Street is mandatory")
                          String street,
                          @NotBlank(message = "Number is required")
                          @Size(min = 2, message = "Number must contain 2 characters")
                          String number,
                          @NotBlank(message = "Neighborhood is required")
                          String neighborhood,
                          @NotBlank(message = "City is required")
                          String city,
                          @NotBlank(message = "State is required")
                          String state) {

    public Address toEntity() {
        return new Address(street, number, neighborhood, city, state);
    }
}
