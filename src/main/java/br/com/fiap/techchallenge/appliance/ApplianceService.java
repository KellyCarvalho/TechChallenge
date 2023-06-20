package br.com.fiap.techchallenge.appliance;

import br.com.fiap.techchallenge.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ApplianceService {

    private final ApplianceRepository applianceRepository;

    public ApplianceService(ApplianceRepository applianceRepository) {
        this.applianceRepository = applianceRepository;
    }

    public ApplianceView update(Long id, ApplianceForm applianceForm) {
        Appliance appliance = applianceRepository.findById(id).orElseThrow(() -> new NotFoundException("Appliance id: %s not found.".formatted(id)));
        appliance.setName(applianceForm.name());
        appliance.setBrand(applianceForm.brand());
        appliance.setModel(applianceForm.model());
        appliance.setPotencyInWatts(applianceForm.potencyInWatts());
        appliance.setVoltage(applianceForm.voltage());

        return new ApplianceView(appliance);
    }
}
