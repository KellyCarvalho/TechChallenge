package br.com.fiap.techchallenge.appliance;

import br.com.fiap.techchallenge.address.Address;
import br.com.fiap.techchallenge.address.AddressRepository;
import br.com.fiap.techchallenge.exception.NotFoundException;
import br.com.fiap.techchallenge.person.Person;
import br.com.fiap.techchallenge.person.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApplianceService {

    private final ApplianceRepository applianceRepository;
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    public ApplianceService(ApplianceRepository applianceRepository, AddressRepository addressRepository,
                            PersonRepository personRepository) {
        this.applianceRepository = applianceRepository;
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }

    public ApplianceView findById(Long id) {
        Appliance appliance = applianceRepository.findById(id).orElseThrow(() -> new NotFoundException("Appliance id: %s not found.".formatted(id)));

        return new ApplianceView(appliance);
    }

    public Collection<ApplianceView> findAll() {
        Collection<Appliance> appliances = applianceRepository.findAll();

        return appliances.stream().map(ApplianceView::new).toList();
    }

    public List<ApplianceView> searchBy(ApplianceSearchForm applianceSearchForm) {
        Collection<Appliance> appliances = applianceRepository.searchBy(applianceSearchForm);

        return appliances.stream().map(ApplianceView::new).toList();
    }

    public ApplianceView createAppliance(ApplianceForm applianceForm) {
        Optional<Person> possiblePerson = applianceForm.personId().flatMap(personRepository::findById);
        Address address = addressRepository.findById(applianceForm.addressId()).orElseThrow(() -> new NotFoundException("Address id: %s not found.".formatted(applianceForm.addressId())));

        if (possiblePerson.isPresent() && !possiblePerson.get().hasAddress(address)) {
            throw new NotFoundException("Person id: %s not found in address id: %s.".formatted(applianceForm.personId(), applianceForm.addressId()));
        }

        Appliance appliance = applianceRepository.save(applianceForm.toEntity(possiblePerson.orElse(null), address));

        return new ApplianceView(appliance);
    }

    public ApplianceView update(Long id, ApplianceForm applianceForm) {
        Appliance appliance = applianceRepository.findById(id).orElseThrow(() -> new NotFoundException("Appliance id: %s not found.".formatted(id)));

        Optional<Person> possiblePerson = applianceForm.personId().flatMap(personRepository::findById);
        Address address = addressRepository.findById(applianceForm.addressId()).orElseThrow(() -> new NotFoundException("Address id: %s not found.".formatted(applianceForm.addressId())));

        if (possiblePerson.isPresent() && !possiblePerson.get().hasAddress(address)) {
            throw new NotFoundException("Person id: %s not found in address id: %s.".formatted(applianceForm.personId(), applianceForm.addressId()));
        }

        appliance.update(applianceForm, possiblePerson.orElse(null), address);

        return new ApplianceView(appliance);
    }

    public void deleteById(Long id) {
        applianceRepository.findById(id).orElseThrow(() -> new NotFoundException("Appliance id: %s not found.".formatted(id)));
        applianceRepository.deleteById(id);
    }
}
