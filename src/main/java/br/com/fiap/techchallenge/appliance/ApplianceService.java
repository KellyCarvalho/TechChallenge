package br.com.fiap.techchallenge.appliance;

import br.com.fiap.techchallenge.address.Address;
import br.com.fiap.techchallenge.address.AddressRepository;
import br.com.fiap.techchallenge.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ApplianceService {

    private final ApplianceRepository applianceRepository;
    private final AddressRepository addressRepository;

    public ApplianceService(ApplianceRepository applianceRepository, AddressRepository addressRepository) {
        this.applianceRepository = applianceRepository;
        this.addressRepository = addressRepository;
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
        Address address = addressRepository.findById(applianceForm.addressId()).orElseThrow(() -> new NotFoundException("Address id: %s not found.".formatted(applianceForm.addressId())));

        Appliance appliance = applianceRepository.save(applianceForm.toEntity(address));

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

    public List<ApplianceView> searchBy(ApplianceSearchForm applianceSearchForm) {
        Collection<Appliance> appliances = applianceRepository.searchBy(applianceSearchForm);

        return appliances.stream().map(ApplianceView::new).toList();
    }
}
