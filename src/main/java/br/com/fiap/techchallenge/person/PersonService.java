package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.address.Address;
import br.com.fiap.techchallenge.address.AddressRepository;
import br.com.fiap.techchallenge.exception.NotFoundException;
import br.com.fiap.techchallenge.user.User;
import br.com.fiap.techchallenge.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public PersonService(PersonRepository personRepository, UserRepository userRepository,
                         AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public PersonView findById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person id: %s not found.".formatted(id)));
        return new PersonView(person);
    }

    public List<PersonView> findAll() {
        Collection<Person> people = personRepository.findAll();
        return people.stream().map(PersonView::new).toList();
    }

    public Collection<PersonView> searchBy(PersonSearchForm personSearchForm) {
        Collection<Person> people = personRepository.searchBy(personSearchForm);
        return people.stream().map(PersonView::new).toList();
    }

    public PersonView create(PersonForm personForm) {
        User user = userRepository.findById(personForm.userId()).orElseThrow(() -> new NotFoundException("User id: %s not found.".formatted(personForm.userId())));
        List<Address> addresses = addressRepository.findAllById(personForm.addressesIds());

        if (addresses.size() != personForm.addressesIds().size()) {
            throw new NotFoundException("One or more addresses not found.");
        }

        Person person = personRepository.save(personForm.toEntity(user, addresses));

        return new PersonView(person);
    }

    public PersonView update(PersonForm personForm, Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person id: %s not found.".formatted(id)));
        person.update(personForm);

        return new PersonView(person);
    }

    public void deleteById(Long id) {
        personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person id: %s not found.".formatted(id)));
        personRepository.deleteById(id);
    }
}
