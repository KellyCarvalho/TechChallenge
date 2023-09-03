package br.com.fiap.techchallenge.address;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@Tag(name = "Address", description = "Manipula dados de endereços")
@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(summary = "Retorna uma lista de endereços", responses = @ApiResponse(responseCode = "200", description = "Sucesso no retorno da lista"))
    @GetMapping
    ResponseEntity<Collection<AddressView>> findAll() {
       Collection<AddressView> addressViews = addressService.findAll();
        return ResponseEntity.ok().body(addressViews);
    }

    @Operation(summary = "Retorna um endereço específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Caso o endereço tenha sido encontrado na base"),
                    @ApiResponse(responseCode = "404", description = "Caso o endereço não tenha sido encontrado na base")
            })
    @GetMapping(value = "/{id}")
    ResponseEntity<AddressView> findById(@PathVariable Long id) {
        AddressView addressView = addressService.findById(id);
        return ResponseEntity.ok(addressView);
    }

    @Operation(summary = "Busca os membros do endereço",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Caso o endereço tenha sido encontrado na base"),
                    @ApiResponse(responseCode = "404", description = "Caso o endereço não tenha sido encontrado na base")
            })
    @GetMapping(value = "/{id}/members")
    ResponseEntity<AddressMembersView> members(@PathVariable Long id) {
        AddressMembersView members = addressService.findMembers(id);
        return ResponseEntity.ok(members);
    }

    @Operation(summary = "Retorna a busca de um endereco por campo",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Caso o endereço tenha sido encontrado na base"),
                    @ApiResponse(responseCode = "404", description = "Caso o endereço não tenha sido encontrado na base")
            })
    @PostMapping(value = "/search")
    ResponseEntity<Collection<AddressView>> search(@RequestBody AddressSearchForm addressSearchForm) {
        Collection<AddressView> addressView = addressService.searchBy(addressSearchForm);
        return ResponseEntity.ok(addressView);
    }

    @Operation(summary = "Cria um endereço na base de dados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Sucesso ao criar o endereço na base"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação")
            })
    @PostMapping
    public ResponseEntity<AddressView> createAddress(@Valid @RequestBody AddressForm addressForm) {
        AddressView addressView = addressService.createAddress(addressForm);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addressView.id()).toUri();
        return ResponseEntity.created(uri).body(addressView);
    }

    @Operation(summary = "Atualiza um endereço na base de dados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar endereço na base"),
                    @ApiResponse(responseCode = "404", description = "Endereço buscado não encontrado")
            })
    @PutMapping(value = "/{id}")
    public ResponseEntity<AddressView> update(@PathVariable Long id, @RequestBody AddressForm addressForm) {
        AddressView addressView = addressService.update(id, addressForm);
        return ResponseEntity.ok(addressView);
    }

    @Operation(summary = "Deleta um endereço na base de dados",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Sucesso ao deletar um endereço"),
                    @ApiResponse(responseCode = "404", description = "Endereço buscado não encontrado")
            })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
