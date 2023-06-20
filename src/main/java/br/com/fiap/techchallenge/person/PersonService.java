package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonView findById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person id: %s not found.".formatted(id)));
        return new PersonView(person);
    }

    public List<PersonView> findAll() {
        Collection<Person> people = personRepository.findAll();
        return people.stream().map(PersonView::new).toList();
    }

    public PersonView create(PersonForm personForm) {
        Person person = personRepository.save(personForm.toEntity());
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
