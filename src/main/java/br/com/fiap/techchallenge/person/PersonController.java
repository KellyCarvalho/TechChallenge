package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonCollectionRepository personRepository;

    public PersonController(PersonCollectionRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/{id}")
    ResponseEntity<PersonView> getPerson(@PathVariable Long id) {
        Person person = personRepository.findById(id).orElseThrow(NotFoundException::new);

        return ResponseEntity.ok(new PersonView(person));
    }

    @GetMapping
    ResponseEntity<List<PersonView>> getPeople() {
        Collection<Person> people = personRepository.findAll();
        List<PersonView> peopleView = people.stream().map(PersonView::new).toList();

        return ResponseEntity.ok(peopleView);
    }

    @PostMapping
    ResponseEntity<PersonView> createPerson(@Valid @RequestBody PersonForm personForm) {
        Person person = personRepository.save(personForm.toEntity());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(new PersonView(person));
    }

    @PutMapping("/{id}")
    ResponseEntity<PersonView> updatePerson(@Valid @RequestBody PersonForm personForm, @PathVariable Long id) {
        Person person = personRepository.findById(id).orElseThrow(NotFoundException::new);
        person.update(personForm);
        personRepository.save(person);

        return ResponseEntity.ok(new PersonView(person));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePerson(@PathVariable Long id) {
        Person person = personRepository.findById(id).orElseThrow(NotFoundException::new);
        personRepository.delete(person.getId());

        return ResponseEntity.noContent().build();
    }
}
