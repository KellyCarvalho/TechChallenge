package br.com.fiap.techchallenge.address;

import java.util.Collection;

public interface AddressRepositoryCustom {

    Collection<Address> searchBy(AddressSearchForm addressSearchForm);
}
