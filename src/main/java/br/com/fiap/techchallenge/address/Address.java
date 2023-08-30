package br.com.fiap.techchallenge.address;

import br.com.fiap.techchallenge.appliance.Appliance;
import br.com.fiap.techchallenge.person.Person;
import br.com.fiap.techchallenge.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Street is mandatory")
    private String street;

    @NotBlank(message = "Number is required")
    @Size(min = 1, message = "Number must contain 1 characters")
    private String number;

    @NotBlank(message = "Neighborhood is required")
    private String neighborhood;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;
    @NotBlank(message = "CEP is required")
    private String cep;

    @ManyToOne
    private User user;

    @ManyToMany(mappedBy = "addresses")
    private Collection<Person> people = new ArrayList<>();

    @OneToMany(mappedBy = "address")
    private Collection<Appliance> appliances;

    public Address() {
    }

    public Address(String street, String number, String neighborhood, String city, String state, String cep) {
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.cep = cep;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Collection<Person> getPeople() {
        return Collections.unmodifiableCollection(people);
    }

    public void addPerson(Person person) {
        this.people.add(person);
    }

    public Collection<Appliance> getAppliances() {
        return Collections.unmodifiableCollection(appliances);
    }

    public void addAppliance(Appliance appliance) {
        this.appliances.add(appliance);
    }
}
