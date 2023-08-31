package br.com.fiap.techchallenge.appliance;

import br.com.fiap.techchallenge.address.Address;
import br.com.fiap.techchallenge.person.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class ApplianceRepositoryCustomImpl implements ApplianceRepositoryCustom {

    private final EntityManager entityManager;

    public ApplianceRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Collection<Appliance> searchBy(ApplianceSearchForm applianceSearchForm) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Appliance> query = criteriaBuilder.createQuery(Appliance.class);

        Root<Appliance> appliance = query.from(Appliance.class);
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(applianceSearchForm.name())) {
            predicates.add(criteriaBuilder.like(appliance.get("name"), "%" + applianceSearchForm.name() + "%"));
        }

        if (Objects.nonNull(applianceSearchForm.brand())) {
            predicates.add(criteriaBuilder.like(appliance.get("brand"), "%" + applianceSearchForm.brand() + "%"));
        }

        if (Objects.nonNull(applianceSearchForm.model())) {
            predicates.add(criteriaBuilder.like(appliance.get("model"), "%" + applianceSearchForm.model() + "%"));
        }

        if (Objects.nonNull(applianceSearchForm.potencyInWatts())) {
            predicates.add(criteriaBuilder.like(appliance.get("city"), "%" + applianceSearchForm.potencyInWatts() + "%"));
        }

        if (Objects.nonNull(applianceSearchForm.voltage())) {
            predicates.add(criteriaBuilder.like(appliance.get("voltage"), "%" + applianceSearchForm.voltage() + "%"));
        }

        if (Objects.nonNull(applianceSearchForm.personId())) {
            Join<Appliance, Person> personJoin = appliance.join("person");
            predicates.add(criteriaBuilder.equal(personJoin.get("id"), applianceSearchForm.personId()));
        }

        if (Objects.nonNull(applianceSearchForm.addressId())) {
            Join<Appliance, Address> addressJoin = appliance.join("address");
            predicates.add(criteriaBuilder.equal(addressJoin.get("id"), applianceSearchForm.addressId()));
        }

        query.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }
}
