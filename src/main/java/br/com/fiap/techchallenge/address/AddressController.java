package br.com.fiap.techchallenge.address;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAddresses(){
        List<Address> addresses = addressRepository.findAll();
        List<AddressDTO> addressDTOS = addresses.stream().map(AddressDTO::new).toList();
        return ResponseEntity.ok().body(addressDTOS);
    }

    @PostMapping
    public ResponseEntity<?> createAddress(@RequestBody AddressDTO addressDTO){
        Set<ConstraintViolation<AddressDTO>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(addressDTO);

        Map<Path, String> violationsMap = violations.stream().collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));

        if (!violationsMap.isEmpty()) return ResponseEntity.badRequest().body(violationsMap);

        Address address = addressRepository.save(addressDTO.toEntity());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).body(address);
    }
}
