package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.*;

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

        Root<Person> person = query.from(Person.class);
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(personSearchForm.userId())) {
            Join<Person, User> userJoin = person.join("user");
            predicates.add(criteriaBuilder.equal(userJoin.get("id"), personSearchForm.userId()));
        }

        if (Objects.nonNull(personSearchForm.connectionToUser())) {
            predicates.add(criteriaBuilder.equal(person.get("connectionToUser"), personSearchForm.connectionToUser()));
        }

        if (Objects.nonNull(personSearchForm.name())) {
            predicates.add(criteriaBuilder.like(person.get("name"), "%" + personSearchForm.name() + "%"));
        }

        if (Objects.nonNull(personSearchForm.gender())) {
            predicates.add(criteriaBuilder.like(person.get("gender"), "%" + personSearchForm.gender() + "%"));
        }

        query.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }
}
