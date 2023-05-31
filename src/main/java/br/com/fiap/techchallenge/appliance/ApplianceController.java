package br.com.fiap.techchallenge.appliance;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/appliances")
public class ApplianceController {

    private final ApplianceCollectionRepository applianceCollectionRepository;

    public ApplianceController(ApplianceCollectionRepository applianceCollectionRepository) {
        this.applianceCollectionRepository = applianceCollectionRepository;
    }

    @GetMapping
    public ResponseEntity<Collection<Appliance>> findAll() {
        Collection<Appliance> appliances = applianceCollectionRepository.findAll();

        return ResponseEntity.ok(appliances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appliance> findById(@PathVariable("id") Long id) {
        Appliance appliance = applianceCollectionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(appliance);
    }
    @PostMapping
    public ResponseEntity<Appliance> create(@RequestBody Appliance applianceForm) {
        Appliance appliance = applianceCollectionRepository.save(applianceForm);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(applianceForm.getId()).toUri();

        return ResponseEntity.created(uri).body(appliance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appliance> update(@PathVariable("id")Long id, @RequestBody Appliance applianceForm) {

        // todo continuar a partir daqui, lembrar que todos os Apliances estão em uma lista, então precisa modificar a lista um item na lista
        Appliance appliance = applianceCollectionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        appliance = applianceForm.merge(appliance, applianceForm);
        applianceCollectionRepository.save(appliance);

        return ResponseEntity.accepted().body(appliance);
    }


}
