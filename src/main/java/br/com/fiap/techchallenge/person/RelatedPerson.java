package br.com.fiap.techchallenge.person;

import jakarta.persistence.*;

@Entity
public class RelatedPerson {

    @EmbeddedId
    private RelatedPersonId id = new RelatedPersonId();

    @ManyToOne
    @MapsId("personId")
    private Person person;

    @ManyToOne
    @MapsId("relatedPersonId")
    private Person relatedPerson;

    @Enumerated(EnumType.STRING)
    private Connection connection;

    @Deprecated
    public RelatedPerson() {}

    public RelatedPerson(Person person, Person relatedPerson, Connection connection) {
        this.person = person;
        this.relatedPerson = relatedPerson;
        this.connection = connection;
    }

    public RelatedPerson createInverseRelation() {
        return new RelatedPerson(relatedPerson, person, Connection.inverseConnection(connection));
    }
}
