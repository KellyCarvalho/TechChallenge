package br.com.fiap.techchallenge.address;


public record AddressView(Long id, String street, String number, String neighborhood, String city, String state, String cep) {

    public AddressView(Address address) {
        this(address.getId(), address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getState(), address.getCep());
    }
}
