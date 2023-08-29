package br.com.fiap.techchallenge.person.relatedPerson;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelatedPersonRepository extends JpaRepository<RelatedPerson, RelatedPersonId> {
    List<RelatedPerson> findAllById_RelatedPersonId(Long personId);
}