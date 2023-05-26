package br.com.fiap.techchallenge.address;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Rua é obrigatória")
    private String street;
    @NotBlank(message = "Número é obrigatório")
    @Size(min = 2, message = "Número deve conter 2 caracteres")
    private String number;
    @NotBlank(message = "Bairro é obrigatório")
    private String neighborhood;
    @NotBlank(message = "Cidade é obrigatório")
    private String city;
    @NotBlank(message = "Estado é obrigatório")
    private String state;

    public Address() {}

    public Address(String street, String number, String neighborhood, String city, String state) {
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

    public void toEntity(AddressDTO addressDTO){
        this.street = addressDTO.street();
        this.number = addressDTO.number();
        this.neighborhood = addressDTO.neighborhood();
        this.city = addressDTO.city();
        this.state = addressDTO.state();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
