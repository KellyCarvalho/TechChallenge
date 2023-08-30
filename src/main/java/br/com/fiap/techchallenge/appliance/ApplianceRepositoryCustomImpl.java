package br.com.fiap.techchallenge.appliance;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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

        query.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
    }
}
