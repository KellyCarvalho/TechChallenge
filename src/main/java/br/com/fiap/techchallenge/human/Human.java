package br.com.fiap.techchallenge.human;

import br.com.fiap.techchallenge.address.Address;
import br.com.fiap.techchallenge.person.Gender;
import br.com.fiap.techchallenge.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany
    private Collection<Address> addresses = new ArrayList<>();

    @Deprecated
    public Human() {
    }

    public Human(String name, LocalDate birthDate, Gender gender) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Human(String name, LocalDate birthDate, Gender gender, List<Address> addresses) {
        this(name, birthDate, gender);
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Collection<Address> getAddresses() {
        return Collections.unmodifiableCollection(addresses);
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public boolean hasAddress(Address address) {
        return this.addresses.contains(address);
    }
}
