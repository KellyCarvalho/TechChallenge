package br.com.fiap.techchallenge.address;

import br.com.fiap.techchallenge.person.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AddressRepositoryCustomImpl implements AddressRepositoryCustom {

    private final EntityManager entityManager;

    public AddressRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Collection<Address> searchBy(AddressSearchForm addressSearchForm) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> query = criteriaBuilder.createQuery(Address.class);

        Root<Address> address = query.from(Address.class);
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(addressSearchForm.personId())) {
            Join<Address, Person> personJoin = address.join("people");
            predicates.add(criteriaBuilder.equal(personJoin.get("id"), addressSearchForm.personId()));
        }

        if (Objects.nonNull(addressSearchForm.street())) {
            predicates.add(criteriaBuilder.like(address.get("street"), "%" + addressSearchForm.street() + "%"));
        }

        if (Objects.nonNull(addressSearchForm.number())) {
            predicates.add(criteriaBuilder.like(address.get("number"), "%" + addressSearchForm.number() + "%"));
        }

        if (Objects.nonNull(addressSearchForm.neighborhood())) {
            predicates.add(criteriaBuilder.like(address.get("neighborhood"), "%" + addressSearchForm.neighborhood() + "%"));
        }

        if (Objects.nonNull(addressSearchForm.city())) {
            predicates.add(criteriaBuilder.like(address.get("city"), "%" + addressSearchForm.city() + "%"));
        }

        if (Objects.nonNull(addressSearchForm.state())) {
            predicates.add(criteriaBuilder.like(address.get("state"), "%" + addressSearchForm.state() + "%"));
        }

        query.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }
}
