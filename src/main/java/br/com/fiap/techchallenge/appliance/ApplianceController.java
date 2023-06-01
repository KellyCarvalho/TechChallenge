package br.com.fiap.techchallenge.appliance;

import jakarta.validation.Valid;
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
    private final ApplianceService applianceService;

    public ApplianceController(ApplianceCollectionRepository applianceCollectionRepository, ApplianceService applianceService) {
        this.applianceCollectionRepository = applianceCollectionRepository;
        this.applianceService = applianceService;
    }

    @GetMapping
    public ResponseEntity<Collection<ApplianceView>> findAll() {
        Collection<Appliance> appliances = applianceCollectionRepository.findAll();
        Collection<ApplianceView> appliancesView = appliances.stream().map(ApplianceView::new).toList();

        return ResponseEntity.ok(appliancesView);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplianceView> findById(@PathVariable("id") Long id) {
        Appliance appliance = applianceCollectionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(new ApplianceView(appliance));
    }

    @PostMapping
    public ResponseEntity<ApplianceView> create(@Valid @RequestBody ApplianceForm applianceForm) {
        Appliance appliance = applianceCollectionRepository.save(applianceForm.toEntity());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(appliance.getId()).toUri();
        return ResponseEntity.created(uri).body(new ApplianceView(appliance));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplianceView> update(@PathVariable Long id, @Valid @RequestBody ApplianceForm applianceForm) {
        ApplianceView applianceView = applianceService.update(id, applianceForm);

        return ResponseEntity.ok(applianceView);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        applianceCollectionRepository.deleteById(id);

        return ResponseEntity.ok("delete com sucesso");
    }

}
