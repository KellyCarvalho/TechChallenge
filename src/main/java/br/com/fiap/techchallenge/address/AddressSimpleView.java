package br.com.fiap.techchallenge.address;


import java.util.Collection;

public record AddressSimpleView(Long id, String street, String number, String neighborhood, String city, String state) {

    public AddressSimpleView(Address address) {
        this(address.getId(), address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getState());
    }

    public static Collection<AddressSimpleView> fromList(Collection<Address> addresses) {
        return addresses.stream().map(AddressSimpleView::new).toList();
    }
}
