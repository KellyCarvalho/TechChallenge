package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.exception.NotFoundException;
import br.com.fiap.techchallenge.person.relatedPerson.RelatedPerson;
import br.com.fiap.techchallenge.person.relatedPerson.RelatedPersonRepository;
import br.com.fiap.techchallenge.user.User;
import br.com.fiap.techchallenge.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final RelatedPersonRepository relatedPersonRepository;
    private final UserRepository userRepository;

    public PersonService(PersonRepository personRepository, RelatedPersonRepository relatedPersonRepository, UserRepository userRepository) {
        this.personRepository = personRepository;
        this.relatedPersonRepository = relatedPersonRepository;
        this.userRepository = userRepository;
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

        User user = userRepository.getReferenceById(personForm.userId());
        RelatedPerson relatedPerson = personForm.relatedPersonEntity(user, person);
        relatedPersonRepository.save(relatedPerson);

        return new PersonView(person);
    }

    public PersonView update(PersonForm personForm, Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person id: %s not found.".formatted(id)));
        person.update(personForm);

        return new PersonView(person);
    }

    public void deleteById(Long id) {
        personRepository.findById(id).orElseThrow(() -> new NotFoundException("Person id: %s not found.".formatted(id)));
        relatedPersonRepository.deleteAll(relatedPersonRepository.findAllById_RelatedPersonId(id));

        personRepository.deleteById(id);
    }
}
