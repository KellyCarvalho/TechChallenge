package br.com.fiap.techchallenge.address;

import jakarta.validation.constraints.NotBlank;

public record AddressDTO(Long id,
         @NotBlank(message = "Rua é obrigatória")
         String street,
         @NotBlank(message = "Número é obrigatório")
         String number,
         @NotBlank(message = "Bairro é obrigatório")
         String neighborhood,
         @NotBlank(message = "Cidade é obrigatório")
         String city,
         @NotBlank(message = "Estado é obrigatório")
         String state) {
    public AddressDTO(Address address) {
        this(address.getId(), address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getState());
    }
}
