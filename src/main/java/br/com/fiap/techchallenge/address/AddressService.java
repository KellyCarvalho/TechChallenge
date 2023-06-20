package br.com.fiap.techchallenge.address;

import br.com.fiap.techchallenge.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Collection<AddressView> findAll() {
        Collection<Address> addresses = addressRepository.findAll();
        return addresses.stream().map(AddressView::new).toList();
    }

    public AddressView findById(Long id) throws NotFoundException {
        Address address = addressRepository.findById(id).orElseThrow(() -> new NotFoundException("Address id: %s not found.".formatted(id)));
        return new AddressView(address);
    }

    public AddressView createAddress(AddressForm addressForm) {
        Address address = addressRepository.save(addressForm.toEntity());
        return new AddressView(address);
    }

    public AddressView update(Long id, AddressForm addressForm) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new NotFoundException("Address id: %s not found.".formatted(id)));
        address.setCity(addressForm.city());
        address.setNeighborhood(addressForm.neighborhood());
        address.setState(addressForm.state());
        address.setStreet(addressForm.street());
        address.setNumber(addressForm.number());
        return new AddressView(address);
    }

    public void delete(Long id) {
        addressRepository.findById(id).orElseThrow(() -> new NotFoundException("Address id: %s not found.".formatted(id)));
        addressRepository.deleteById(id);
    }
}
