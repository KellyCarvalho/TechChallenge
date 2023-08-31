package br.com.fiap.techchallenge.address;


import br.com.fiap.techchallenge.person.PersonView;
import br.com.fiap.techchallenge.user.UserView;

import java.util.Collection;

public record AddressView(Long id, String street, String number, String neighborhood, String city, String state, String cep, UserView userView, Collection<PersonView> peopleView) {

    public AddressView(Address address) {
        this(address.getId(), address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getState(), address.getCep(), new UserView(address.getUser()), PersonView.toPersonViewList(address.getPeople()));
    }
}
