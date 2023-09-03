package br.com.fiap.techchallenge.user;

import br.com.fiap.techchallenge.human.Human;
import br.com.fiap.techchallenge.person.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
public class User extends Human {

    @OneToMany(mappedBy = "user")
    private Collection<Person> relatedPeople = new HashSet<>();

    @Deprecated
    public User() {
    }

    public void update(UserForm userForm) {
        setName(userForm.name());
        setBirthDate(userForm.birthDate());
        setGender(userForm.gender());
    }

    public User(String name, LocalDate birthDate, Gender gender) {
        super(name, birthDate, gender);
    }

    public Collection<Person> getRelatedPeople() {
        return Collections.unmodifiableCollection(relatedPeople);
    }

    public void addRelatedPersons(Person relatedPersons) {
        this.relatedPeople.add(relatedPersons);
    }
}
