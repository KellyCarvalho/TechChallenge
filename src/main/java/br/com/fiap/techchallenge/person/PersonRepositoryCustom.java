package br.com.fiap.techchallenge.person;

import java.util.Collection;

public interface PersonRepositoryCustom {

    Collection<Person> searchBy(PersonSearchForm personSearchForm);

}
