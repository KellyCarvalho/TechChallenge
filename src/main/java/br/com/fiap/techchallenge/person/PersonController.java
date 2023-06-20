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

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @Operation(description = "Retorna uma pessoa específica",
            responses = {
                @ApiResponse(responseCode = "200", description = "Caso a pessoa tenha sido encontrada na base"),
                @ApiResponse(responseCode = "404", description = "Caso a pessoa não tenha sido encontrada na base")
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<PersonView> getPerson(@PathVariable Long id) {
        PersonView personView = personService.findById(id);

        return ResponseEntity.ok(personView);
    }


    @Operation(description = "Retorna uma lista de pessoas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sucesso no retorno da lista")
            }
    )
    @GetMapping
    ResponseEntity<List<PersonView>> getPeople() {
        List<PersonView> peopleView = personService.findAll();

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
        PersonView personView = personService.create(personForm);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(personView.id()).toUri();
        return ResponseEntity.created(uri).body(personView);
    }


    @Operation(description = "Atualiza uma pessoa na base de dados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Caso a pessoa tenha sido encontrada na base"),
                    @ApiResponse(responseCode = "404", description = "Caso a pessoa buscada não seja encontrada")
            }
    )
    @PutMapping("/{id}")
    ResponseEntity<PersonView> updatePerson(@Valid @RequestBody PersonForm personForm, @PathVariable Long id) {
        PersonView personView = personService.update(personForm, id);

        return ResponseEntity.ok(personView);
    }

    @Operation(description = "Deleta uma pessoa na base de dados",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Ao dletar uma pessoa"),
                    @ApiResponse(responseCode = "404", description = "Caso a pessoa buscada não seja encontrada")
            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePerson(@PathVariable Long id) {
        personService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
