package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.address.Address;
import br.com.fiap.techchallenge.human.Human;
import br.com.fiap.techchallenge.person.FamilyRelation.FamilyRelation;
import br.com.fiap.techchallenge.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
public class Person extends Human {

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private Connection connectionToUser;

    @OneToMany(mappedBy = "familyMember")
    private Collection<FamilyRelation> familyRelation;

    @Deprecated
    public Person() {
    }

    public Person(String name, LocalDate birthDate, Gender gender, User user, Connection connectionToUser, List<Address> addresses) {
        super(name, birthDate, gender, addresses);
        this.user = user;
        this.connectionToUser = connectionToUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Connection getConnectionToUser() {
        return connectionToUser;
    }

    public Connection getUserConnection() {
        return connectionToUser.inverseConnection();
    }

    public void setConnectionToUser(Connection connectionToUser) {
        this.connectionToUser = connectionToUser;
    }

    public Collection<FamilyRelation> getFamilyRelation() {
        return Collections.unmodifiableCollection(familyRelation);
    }

    public void addFamilyRelation(FamilyRelation familyRelation) {
        this.familyRelation.add(familyRelation);
    }

    public void update(PersonForm personForm) {
        setName(personForm.name());
        setBirthDate(personForm.birthDate());
        setGender(personForm.gender());
        setConnectionToUser(personForm.connectionToUser());
    }
}
