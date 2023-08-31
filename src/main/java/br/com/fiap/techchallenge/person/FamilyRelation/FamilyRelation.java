package br.com.fiap.techchallenge.person.FamilyRelation;

import br.com.fiap.techchallenge.person.Connection;
import br.com.fiap.techchallenge.person.Person;
import jakarta.persistence.*;

@Entity
public class FamilyRelation {

    @EmbeddedId
    private FamilyRelationId id = new FamilyRelationId();

    @ManyToOne
    @MapsId("personId")
    private Person person;

    @ManyToOne
    @MapsId("familyMemberId")
    private Person familyMember;

    private Connection connection;

    public FamilyRelation() {
    }

    public FamilyRelation(Person person, Person familyMember, Connection connection) {
        this.person = person;
        this.familyMember = familyMember;
        this.connection = connection;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
