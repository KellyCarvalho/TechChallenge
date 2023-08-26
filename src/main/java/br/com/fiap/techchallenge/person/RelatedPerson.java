package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.user.User;
import jakarta.persistence.*;

@Entity
public class RelatedPerson {

    @EmbeddedId
    private RelatedPersonId id = new RelatedPersonId();

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
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
}
