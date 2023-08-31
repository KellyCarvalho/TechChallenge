package br.com.fiap.techchallenge.address;


import br.com.fiap.techchallenge.person.PersonView;
import br.com.fiap.techchallenge.user.UserView;

import java.util.Collection;

public record AddressMembersView(Long id, UserView user, Collection<PersonView> people) {

    public AddressMembersView(Address address) {
        this(address.getId(), new UserView(address.getUser()), PersonView.fromList(address.getPeople()));
    }
}
