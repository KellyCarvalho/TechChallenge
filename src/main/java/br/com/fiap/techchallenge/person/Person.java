package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.address.Address;
import br.com.fiap.techchallenge.human.Human;
import br.com.fiap.techchallenge.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
public class Person extends Human {

    @ManyToOne
    private User user;

    @Deprecated
    public Person() {
    }

    public Person(String name, LocalDate birthDate, Gender gender, User user, List<Address> addresses) {
        super(name, birthDate, gender, addresses);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void update(PersonForm personForm) {
        setName(personForm.name());
        setBirthDate(personForm.birthDate());
        setGender(personForm.gender());
    }
}
