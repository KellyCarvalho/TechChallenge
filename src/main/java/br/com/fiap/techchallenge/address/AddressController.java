package br.com.fiap.techchallenge.address;

import br.com.fiap.techchallenge.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressRepository addressRepository;
    private final AddressService addressService;

    public AddressController(AddressRepository addressRepository, AddressService addressService) {
        this.addressRepository = addressRepository;
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<?> getAddresses() {
        Collection<Address> addresses = addressRepository.findAll();
        Collection<AddressDTO> addressDTOS = addresses.stream().map(AddressDTO::new).toList();
        return ResponseEntity.ok().body(addressDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAddress(@PathVariable Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new NotFoundException("Endereço não encontrado"));
        return ResponseEntity.ok(new AddressDTO(address));
    }

    @PostMapping
    public ResponseEntity<?> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
        Address address = addressRepository.save(addressDTO.toEntity());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).body(address);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        addressDTO = addressService.update(id, addressDTO);
        return ResponseEntity.ok(addressDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        addressRepository.findById(id).orElseThrow(() -> new NotFoundException("Endereço não encontrado"));
        addressRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
