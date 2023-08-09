package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.address.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @OneToMany(mappedBy = "person")
    private Collection<RelatedPerson> relatedPersons = new HashSet<>();

    @ManyToMany
    private Collection<Address> addresses = new ArrayList<>();

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Deprecated
    public Person() {
    }

    public Person(String name, LocalDate birthDate, Gender gender) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
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

    public Collection<Address> getAddresses() {
        return Collections.unmodifiableCollection(addresses);
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public Collection<RelatedPerson> getRelatedPersons() {
        return Collections.unmodifiableCollection(relatedPersons);
    }

    public void addRelatedPersons(RelatedPerson relatedPersons) {
        this.relatedPersons.add(relatedPersons);
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

    public void update(PersonForm personForm) {
        setName(personForm.name());
        setBirthDate(personForm.birthDate());
        setGender(personForm.gender());
    }
}
