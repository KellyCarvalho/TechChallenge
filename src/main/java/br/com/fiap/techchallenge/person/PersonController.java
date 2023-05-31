package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/{id}")
    ResponseEntity<PersonView> getPerson(@PathVariable Long id) {
        Person person = personRepository.findById(id).orElseThrow(NotFoundException::new);

        return ResponseEntity.ok(new PersonView(person));
    }

    @GetMapping
    ResponseEntity<List<PersonView>> getPeople() {
        List<Person> people = personRepository.findAll();
        List<PersonView> peopleView = people.stream().map(PersonView::new).toList();

        return ResponseEntity.ok(peopleView);
    }

    @PostMapping
    ResponseEntity<PersonView> createPerson(@Valid @RequestBody PersonDTO personDTO) {
        Person person = personRepository.save(personDTO.toEntity());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(new PersonView(person));
    }
}
