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
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(description = "Retorna uma lista de endereços",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sucesso no retorno da lista")
            }
    )
    @GetMapping
    public ResponseEntity<Collection<AddressView>> findAll() {
       Collection<AddressView> addressViews = addressService.findAll();
        return ResponseEntity.ok().body(addressViews);
    }

    @Operation(description = "Retorna um endereço específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Caso o endereço tenha sido encontrado na base"),
                    @ApiResponse(responseCode = "404", description = "Caso o endereço não tenha sido encontrado na base")
            }
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<AddressView> findById(@PathVariable Long id) {
        AddressView addressView = addressService.findById(id);
        return ResponseEntity.ok(addressView);
    }

    @Operation(description = "Cria um endereço na base de dados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Sucesso ao criar o endereço na base"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação")
            }
    )
    @PostMapping
    public ResponseEntity<AddressView> createAddress(@Valid @RequestBody AddressForm addressForm) {
        AddressView addressView = addressService.createAddress(addressForm);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addressView.id()).toUri();
        return ResponseEntity.created(uri).body(addressView);
    }

    @Operation(description = "Atualiza um endereço na base de dados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar endereço na base"),
                    @ApiResponse(responseCode = "404", description = "Endereço buscado não encontrado")
            }
    )
    @PutMapping(value = "/{id}")
    public ResponseEntity<AddressView> update(@PathVariable Long id, @RequestBody AddressForm addressForm) {
        AddressView addressView = addressService.update(id, addressForm);
        return ResponseEntity.ok(addressView);
    }

    @Operation(description = "Deleta um endereço na base de dados",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Sucesso ao deletar um endereço"),
                    @ApiResponse(responseCode = "404", description = "Endereço buscado não encontrado")
            }
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
