package br.com.fiap.techchallenge.address;


import br.com.fiap.techchallenge.person.relatedPerson.RelationsView;

public record AddressView(Long id, String street, String number, String neighborhood, String city, String state, String cep, RelationsView relations) {

    public AddressView(Address address) {
        this(address.getId(), address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getState(), address.getCep(), new RelationsView(address.getUser()));
    }
}
