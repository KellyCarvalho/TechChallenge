package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/person")
@Tag(name = "Person", description = "Manipula dados de pessoas")
public class PersonController {

    private final PersonCollectionRepository personRepository;

    public PersonController(PersonCollectionRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Operation(description = "Retorna uma pessoa específica",
            responses = {
                @ApiResponse(responseCode = "200", description = "Caso a pessoa tenha sido encontrada na base"),
                @ApiResponse(responseCode = "404", description = "Caso a pessoa não tenha sido encontrada na base")
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<PersonView> getPerson(@PathVariable Long id) {
        Person person = personRepository.findById(id).orElseThrow(NotFoundException::new);

        return ResponseEntity.ok(new PersonView(person));
    }


    @Operation(description = "Retorna uma lista de pessoas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sucesso no retorno da lista")
            }
    )
    @GetMapping
    ResponseEntity<List<PersonView>> getPeople() {
        Collection<Person> people = personRepository.findAll();
        List<PersonView> peopleView = people.stream().map(PersonView::new).toList();

        return ResponseEntity.ok(peopleView);
    }


    @Operation(description = "Cria uma pessoa na base de dados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Caso a pessoa tenha sido encontrada na base"),
                    @ApiResponse(responseCode = "400", description = "Caso o usuario não tenha permisao de acesso aeste recurso")
            }
    )
    @PostMapping
    ResponseEntity<PersonView> createPerson(@Valid @RequestBody PersonForm personForm) {
        Person person = personRepository.save(personForm.toEntity());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(new PersonView(person));
    }


    @Operation(description = "Atualiza uma pessoa na base de dados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Caso a pessoa tenha sido encontrada na base"),
                    @ApiResponse(responseCode = "404", description = "Caso a pessoa buscada não seja encontrada")
            }
    )
    @PutMapping("/{id}")
    ResponseEntity<PersonView> updatePerson(@Valid @RequestBody PersonForm personForm, @PathVariable Long id) {
        Person person = personRepository.findById(id).orElseThrow(NotFoundException::new);
        person.update(personForm);
        personRepository.save(person);

        return ResponseEntity.ok(new PersonView(person));
    }

    @Operation(description = "Deleta uma pessoa na base de dados",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Ao dletar uma pessoa"),
                    @ApiResponse(responseCode = "404", description = "Caso a pessoa buscada não seja encontrada")
            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePerson(@PathVariable Long id) {
        Person person = personRepository.findById(id).orElseThrow(NotFoundException::new);
        personRepository.delete(person.getId());

        return ResponseEntity.noContent().build();
    }
}
