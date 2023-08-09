package br.com.fiap.techchallenge.address;

import br.com.fiap.techchallenge.exception.NotFoundException;
import br.com.fiap.techchallenge.person.Person;
import br.com.fiap.techchallenge.person.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public AddressService(AddressRepository addressRepository, PersonRepository personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
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
        Person person = personRepository.findById(addressForm.personId()).orElseThrow(() -> new NotFoundException("Person id: %s not found.".formatted(addressForm.personId())));
        Address address = addressRepository.save(addressForm.toEntity());

        person.addAddress(address);
        personRepository.save(person);

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
