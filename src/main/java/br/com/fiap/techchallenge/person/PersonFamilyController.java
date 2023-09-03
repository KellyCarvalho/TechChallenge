package br.com.fiap.techchallenge.person;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
@Tag(name = "Person", description = "Manipula dados de pessoas")
public class PersonFamilyController {

    private final PersonService personService;

    public PersonFamilyController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(summary = "Busca os filhos da pessoa",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Caso o endereço tenha sido encontrado na base"),
                    @ApiResponse(responseCode = "404", description = "Caso o endereço não tenha sido encontrado na base")
            })
    @GetMapping(value = "/{id}/ancestors")
    ResponseEntity<PersonFamilyView> ancestors(@PathVariable Long id) {
        PersonFamilyView ancestors = personService.findParents(id);
        return ResponseEntity.ok(ancestors);
    }

    @Operation(summary = "Busca os filhos da pessoa",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Caso o endereço tenha sido encontrado na base"),
                    @ApiResponse(responseCode = "404", description = "Caso o endereço não tenha sido encontrado na base")
            })
    @GetMapping(value = "/{id}/descendants")
    ResponseEntity<PersonFamilyView> descendants(@PathVariable Long id) {
        PersonFamilyView descendants = personService.findChildren(id);
        return ResponseEntity.ok(descendants);
    }

    @Operation(summary = "Busca os filhos da pessoa",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Caso o endereço tenha sido encontrado na base"),
                    @ApiResponse(responseCode = "404", description = "Caso o endereço não tenha sido encontrado na base")
            })
    @GetMapping(value = "/{id}/brothers")
    ResponseEntity<PersonFamilyView> brothers(@PathVariable Long id) {
        PersonFamilyView brothers = personService.findBrothers(id);
        return ResponseEntity.ok(brothers);
    }

    @Operation(summary = "Atualiza uma pessoa na base de dados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Caso a pessoa tenha sido encontrada na base"),
                    @ApiResponse(responseCode = "404", description = "Caso a pessoa buscada não seja encontrada")
            })
    @PostMapping("/{personId}/parent/{parentId}")
    ResponseEntity<PersonView> addParent(@PathVariable Long personId, @PathVariable Long parentId) {
        PersonView personView = personService.addChild(parentId, personId);

        return ResponseEntity.ok(personView);
    }
}
