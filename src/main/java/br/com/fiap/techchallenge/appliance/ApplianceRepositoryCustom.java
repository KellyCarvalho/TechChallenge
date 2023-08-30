package br.com.fiap.techchallenge.appliance;

import java.util.Collection;

public interface ApplianceRepositoryCustom {

    Collection<Appliance> searchBy(ApplianceSearchForm applianceSearchForm);
}
