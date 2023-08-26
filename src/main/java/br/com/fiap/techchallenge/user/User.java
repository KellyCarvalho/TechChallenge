package br.com.fiap.techchallenge.user;

import br.com.fiap.techchallenge.person.*;
import br.com.fiap.techchallenge.person.relatedPerson.RelatedPerson;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@DiscriminatorValue("USER")
public class User extends Person {

    @OneToMany(mappedBy = "user")
    private Collection<RelatedPerson> relatedPersons = new HashSet<>();

    @Deprecated
    public User() {
    }

    public User(String name, LocalDate birthDate, Gender gender) {
        super(name, birthDate, gender);
    }

    public Collection<RelatedPerson> getRelatedPersons() {
        return Collections.unmodifiableCollection(relatedPersons);
    }

    public void addRelatedPersons(RelatedPerson relatedPersons) {
        this.relatedPersons.add(relatedPersons);
    }
}
