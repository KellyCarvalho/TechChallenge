package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.address.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

    private final EntityManager entityManager;

    public PersonRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Collection<Person> searchBy(PersonSearchForm personSearchForm) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);

        Root<Person> from = query.from(Person.class);
        List<Predicate> predicates = new ArrayList<>();

//        if (Objects.nonNull(personSearchForm.personId())) {
//            Join<Address, Person> personJoin = person.join("people");
//            predicates.add(criteriaBuilder.equal(personJoin.get("id"), personSearchForm.personId()));
//        }

//        if (Objects.nonNull(personSearchForm.street())) {
//            predicates.add(criteriaBuilder.like(person.get("street"), "%" + personSearchForm.street() + "%"));
//        }

//        if (Objects.nonNull(personSearchForm.number())) {
//            predicates.add(criteriaBuilder.like(person.get("number"), "%" + personSearchForm.number() + "%"));
//        }

//        if (Objects.nonNull(personSearchForm.neighborhood())) {
//            predicates.add(criteriaBuilder.like(person.get("neighborhood"), "%" + personSearchForm.neighborhood() + "%"));
//        }

//        if (Objects.nonNull(personSearchForm.city())) {
//            predicates.add(criteriaBuilder.like(person.get("city"), "%" + personSearchForm.city() + "%"));
//        }

//        if (Objects.nonNull(personSearchForm.state())) {
//            predicates.add(criteriaBuilder.like(person.get("state"), "%" + personSearchForm.state() + "%"));
//        }
        return null;
    }
}
