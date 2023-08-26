package br.com.fiap.techchallenge.address;


import br.com.fiap.techchallenge.person.relatedPerson.RelationsView;

public record AddressView(Long id, String street, String number, String neighborhood, String city, String state, RelationsView relations) {

    public AddressView(Address address) {
        this(address.getId(), address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getState(), new RelationsView(address.getUser()));
    }
}
