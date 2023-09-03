package br.com.fiap.techchallenge.person;

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
@RequestMapping("/people")
@Tag(name = "Person", description = "Manipula dados de pessoas")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(summary = "Retorna uma pessoa específica",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Caso a pessoa tenha sido encontrada na base"),
                    @ApiResponse(responseCode = "404", description = "Caso a pessoa não tenha sido encontrada na base")
            })
    @GetMapping("/{id}")
    ResponseEntity<PersonView> findById(@PathVariable Long id) {
        PersonView personView = personService.findById(id);

        return ResponseEntity.ok(personView);
    }


    @Operation(summary = "Retorna uma lista de pessoas", responses = @ApiResponse(responseCode = "200", description = "Sucesso no retorno da lista"))
    @GetMapping
    ResponseEntity<List<PersonView>> findAll() {
        List<PersonView> peopleView = personService.findAll();

        return ResponseEntity.ok(peopleView);
    }

    @Operation(summary = "Busca uma pessoa na base de dados", responses = @ApiResponse(responseCode = "200", description = "Sucesso no retorno da lista"))
    @GetMapping(value = "/search")
    ResponseEntity<Collection<PersonView>> search(@RequestBody PersonSearchForm personSearchForm) {
        Collection<PersonView> peopleView = personService.searchBy(personSearchForm);
        return ResponseEntity.ok(peopleView);
    }

    @Operation(summary = "Cria uma pessoa na base de dados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Caso a pessoa tenha sido encontrada na base"),
                    @ApiResponse(responseCode = "400", description = "Caso o usuario não tenha permissao de acesso aeste recurso")
            })
    @PostMapping
    ResponseEntity<PersonView> create(@Valid @RequestBody PersonForm personForm) {
        PersonView personView = personService.create(personForm);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(personView.id()).toUri();
        return ResponseEntity.created(uri).body(personView);
    }


    @Operation(summary = "Atualiza uma pessoa na base de dados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Caso a pessoa tenha sido encontrada na base"),
                    @ApiResponse(responseCode = "404", description = "Caso a pessoa buscada não seja encontrada")
            })
    @PutMapping("/{id}")
    ResponseEntity<PersonView> update(@Valid @RequestBody PersonForm personForm, @PathVariable Long id) {
        PersonView personView = personService.update(personForm, id);

        return ResponseEntity.ok(personView);
    }

    @Operation(summary = "Deleta uma pessoa na base de dados",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Ao deletar uma pessoa"),
                    @ApiResponse(responseCode = "404", description = "Caso a pessoa buscada não seja encontrada")
            })
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteById(@PathVariable Long id) {
        personService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
