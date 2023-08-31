package br.com.fiap.techchallenge.address;


import br.com.fiap.techchallenge.person.PersonSimpleView;
import br.com.fiap.techchallenge.user.UserView;

import java.util.Collection;

public record AddressView(Long id, String street, String number, String neighborhood, String city, String state, UserView user, Collection<PersonSimpleView> people) {

    public AddressView(Address address) {
        this(address.getId(), address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getState(), new UserView(address.getUser()), PersonSimpleView.fromList(address.getPeople()));
    }
}
