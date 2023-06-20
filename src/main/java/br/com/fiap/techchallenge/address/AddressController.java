package br.com.fiap.techchallenge.address;

import br.com.fiap.techchallenge.exception.NotFoundException;
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

    private final AddressRepository addressRepository;
    private final AddressService addressService;

    public AddressController(AddressRepository addressRepository, AddressService addressService) {
        this.addressRepository = addressRepository;
        this.addressService = addressService;
    }

    @Operation(description = "Retorna uma lista de endereços",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sucesso no retorno da lista")
            }
    )
    @GetMapping
    public ResponseEntity<?> getAddresses() {
        Collection<Address> addresses = addressRepository.findAll();
        Collection<AddressDTO> addressDTOS = addresses.stream().map(AddressDTO::new).toList();
        return ResponseEntity.ok().body(addressDTOS);
    }

    @Operation(description = "Retorna um endereço específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Caso o endereço tenha sido encontrado na base"),
                    @ApiResponse(responseCode = "404", description = "Caso o endereço não tenha sido encontrado na base")
            }
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAddress(@PathVariable Long id) {
        Address address = addressRepository.findById(id).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(new AddressDTO(address));
    }

    @Operation(description = "Cria um endereço na base de dados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Sucesso ao criar o endereço na base"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação")
            }
    )
    @PostMapping
    public ResponseEntity<?> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
        Address address = addressRepository.save(addressDTO.toEntity());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).body(address);
    }

    @Operation(description = "Atualiza um endereço na base de dados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar endereço na base"),
                    @ApiResponse(responseCode = "404", description = "Endereço buscado não encontrado")
            }
    )
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        addressDTO = addressService.update(id, addressDTO);
        return ResponseEntity.ok(addressDTO);
    }

    @Operation(description = "Deleta um endereço na base de dados",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Sucesso ao deletar um endereço"),
                    @ApiResponse(responseCode = "404", description = "Endereço buscado não encontrado")
            }
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        addressRepository.findById(id).orElseThrow(NotFoundException::new);
        addressRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
