package br.com.fiap.techchallenge.address;

import br.com.fiap.techchallenge.exception.NotFoundException;
import br.com.fiap.techchallenge.person.PersonRepository;
import br.com.fiap.techchallenge.user.User;
import br.com.fiap.techchallenge.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;
    private final UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, PersonRepository personRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    public Collection<AddressView> findAll() {
        Collection<Address> addresses = addressRepository.findAll();
        return addresses.stream().map(AddressView::new).toList();
    }

    public AddressView findById(Long id) throws NotFoundException {
        Address address = addressRepository.findById(id).orElseThrow(() -> new NotFoundException("Address id: %s not found.".formatted(id)));
        return new AddressView(address);
    }

    public Collection<AddressView> searchBy(AddressSearchForm addressSearchForm) {
        Collection<Address> addresses = addressRepository.searchBy(addressSearchForm);
        return addresses.stream().map(AddressView::new).toList();
    }

    public AddressView createAddress(AddressForm addressForm) {
        User user = userRepository.findById(addressForm.userId()).orElseThrow(() -> new NotFoundException("User id: %s not found.".formatted(addressForm.userId())));
        Address address = addressRepository.save(addressForm.toEntity(user));

        user.addAddress(address);
        userRepository.save(user);

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
