package br.com.fiap.techchallenge.appliance;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ApplianceService {

    private final ApplianceCollectionRepository applianceCollectionRepository;

    public ApplianceService(ApplianceCollectionRepository applianceCollectionRepository) {
        this.applianceCollectionRepository = applianceCollectionRepository;
    }

    public ApplianceView update(Long id, ApplianceForm applianceForm) {
        Appliance appliance = applianceCollectionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        appliance.setName(applianceForm.name());
        appliance.setBrand(applianceForm.brand());
        appliance.setModel(applianceForm.model());
        appliance.setPotencyInWatts(applianceForm.potencyInWatts());
        appliance.setVoltage(applianceForm.voltage());

        return new ApplianceView(appliance);
    }
}
