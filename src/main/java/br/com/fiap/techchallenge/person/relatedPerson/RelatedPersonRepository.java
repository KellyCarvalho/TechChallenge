package br.com.fiap.techchallenge.person.relatedPerson;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RelatedPersonRepository extends JpaRepository<RelatedPerson, RelatedPersonId> {
}