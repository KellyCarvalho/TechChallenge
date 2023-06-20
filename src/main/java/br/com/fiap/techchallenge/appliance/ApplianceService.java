package br.com.fiap.techchallenge.appliance;

import br.com.fiap.techchallenge.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ApplianceService {

    private final ApplianceRepository applianceRepository;

    public ApplianceService(ApplianceRepository applianceRepository) {
        this.applianceRepository = applianceRepository;
    }

    public Collection<ApplianceView> findAll() {
        Collection<Appliance> appliances = applianceRepository.findAll();

        return appliances.stream().map(ApplianceView::new).toList();
    }

    public ApplianceView findById(Long id) {
        Appliance appliance = applianceRepository.findById(id).orElseThrow(() -> new NotFoundException("Appliance id: %s not found.".formatted(id)));

        return new ApplianceView(appliance);
    }

    public ApplianceView createAppliance(ApplianceForm applianceForm) {
        Appliance appliance = applianceRepository.save(applianceForm.toEntity());

        return new ApplianceView(appliance);
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

    public void deleteById(Long id) {
        applianceRepository.findById(id).orElseThrow(() -> new NotFoundException("Appliance id: %s not found.".formatted(id)));
        applianceRepository.deleteById(id);
    }
}
