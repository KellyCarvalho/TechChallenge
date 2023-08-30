package br.com.fiap.techchallenge.address;


import br.com.fiap.techchallenge.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddressForm(@NotNull(message = "User is mandatory")
                          Long userId,
                          @NotBlank(message = "Street is mandatory")
                          String street,
                          @NotBlank(message = "Number is required")
                          @Size(min = 1, message = "Number must contain 1 characters")
                          String number,
                          @NotBlank(message = "Neighborhood is required")
                          String neighborhood,
                          @NotBlank(message = "City is required")
                          String city,
                          @NotBlank(message = "State is required")
                          String state,
                          @NotBlank(message = "CEP is required")
                          String cep) {

    public Address toEntity(User user) {
        return new Address(street, number, neighborhood, city, state, cep, user);
    }
}
