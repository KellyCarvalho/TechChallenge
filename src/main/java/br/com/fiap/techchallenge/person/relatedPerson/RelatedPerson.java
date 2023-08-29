package br.com.fiap.techchallenge.person.relatedPerson;

import br.com.fiap.techchallenge.person.Person;
import br.com.fiap.techchallenge.user.User;
import jakarta.persistence.*;

@Entity
public class RelatedPerson {

    @EmbeddedId
    private RelatedPersonId id = new RelatedPersonId();

    @ManyToOne
    @MapsId("userId")
    private User user;

    @OneToOne
    @MapsId("relatedPersonId")
    private Person relatedPerson;

    @Enumerated(EnumType.STRING)
    private Connection connection;

    @Deprecated
    public RelatedPerson() {}

    public RelatedPerson(User user, Person relatedPerson, Connection connection) {
        this.user = user;
        this.relatedPerson = relatedPerson;
        this.connection = connection;
    }

    public Person getRelatedPerson() {
        return relatedPerson;
    }

    public void setRelatedPerson(Person relatedPerson) {
        this.relatedPerson = relatedPerson;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
