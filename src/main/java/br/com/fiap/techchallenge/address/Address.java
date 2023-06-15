package br.com.fiap.techchallenge.address;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

public class Address {

    private Long id;
    @NotBlank(message = "Street is mandatory")
    private String street;
    @NotBlank(message = "Number is required")
    @Size(min = 2, message = "Number must contain 2 characters")
    private String number;
    @NotBlank(message = "Neighborhood is required")
    private String neighborhood;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "State is required")
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
