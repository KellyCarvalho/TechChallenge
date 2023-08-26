package br.com.fiap.techchallenge.person.relatedPerson;

import br.com.fiap.techchallenge.person.PersonView;

public record RelatedPersonView(Connection connection, PersonView relatedPerson) {

    public RelatedPersonView(RelatedPerson relatedPerson) {
        this(relatedPerson.getConnection(), new PersonView(relatedPerson.getRelatedPerson()));
    }
}
