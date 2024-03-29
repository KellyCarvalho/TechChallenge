package br.com.fiap.techchallenge.appliance;

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

@Tag(name = "Appliance", description = "Manipula dados de eletrodomésticos")
@RestController
@RequestMapping("/appliances")
public class ApplianceController {

    private final ApplianceService applianceService;

    public ApplianceController(ApplianceService applianceService) {
        this.applianceService = applianceService;
    }

    @Operation(summary = "Retorna uma lista de eletrodomésticos",responses = @ApiResponse(responseCode = "200", description = "Sucesso no retorno da lista"))
    @GetMapping
    ResponseEntity<Collection<ApplianceView>> findAll() {
        Collection<ApplianceView> appliancesView = applianceService.findAll();

        return ResponseEntity.ok(appliancesView);
    }

    @Operation(summary = "Retorna um eletrodoméstico específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Caso o eletrodoméstico tenha sido encontrado na base"),
                    @ApiResponse(responseCode = "404", description = "Caso o eletrodoméstico não tenha sido encontrado na base")
            })
    @GetMapping("/{id}")
    ResponseEntity<ApplianceView> findById(@PathVariable("id") Long id) {
        ApplianceView applianceView = applianceService.findById(id);

        return ResponseEntity.ok(applianceView);
    }

    @Operation(summary = "Cria um eletrodoméstico na base de dados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Sucesso ao criar o eletrodoméstico na base"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação")
            })
    @PostMapping
    ResponseEntity<ApplianceView> create(@Valid @RequestBody ApplianceForm applianceForm) {
        ApplianceView applianceView =  applianceService.createAppliance(applianceForm);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(applianceView.id()).toUri();
        return ResponseEntity.created(uri).body(applianceView);
    }

    @Operation(summary = "Atualiza um eletrodoméstico na base de dados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar eletrodoméstico na base"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação"),
                    @ApiResponse(responseCode = "404", description = "Eletrodoméstico buscado não encontrado")
            })
    @PutMapping("/{id}")
    ResponseEntity<ApplianceView> update(@PathVariable Long id, @Valid @RequestBody ApplianceForm applianceForm) {
        ApplianceView applianceView = applianceService.update(id, applianceForm);

        return ResponseEntity.ok(applianceView);
    }

    @Operation(summary = "Deleta um eletrodoméstico na base de dados",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Sucesso ao deletar um eletrodoméstico"),
                    @ApiResponse(responseCode = "404", description = "Eletrodomestico buscado não encontrado")
            })
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        applianceService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Retorna a busca de um eletrodoméstico por campo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Caso o eletrodoméstico tenha sido encontrado na base"),
                    @ApiResponse(responseCode = "404", description = "Caso o eletrodoméstico não tenha sido encontrado na base")
            })
    @PostMapping("/search")
    ResponseEntity<List<ApplianceView>> searchBy(@RequestBody ApplianceSearchForm applianceSearchForm) {
        List<ApplianceView> appliancesView =  applianceService.searchBy(applianceSearchForm);

        return ResponseEntity.ok(appliancesView);
    }

}
