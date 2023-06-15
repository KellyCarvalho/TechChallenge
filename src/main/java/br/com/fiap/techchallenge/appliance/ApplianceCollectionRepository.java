package br.com.fiap.techchallenge.appliance;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ApplianceCollectionRepository  {

    static private final Set<Appliance> appliances  = new LinkedHashSet<>();

    public Collection<Appliance> findAll() {
        return appliances;
    }

    public Optional<Appliance> findById(Long id) {
        return appliances.stream().filter(appliance -> appliance.getId().equals(id)).findFirst();
    }

    public static Appliance create(Appliance appliance) {
        appliance.setId(appliances.size() + 1L);
        appliances.add(appliance);
        return appliance;
    }

    public Appliance save(Appliance appliance) {
        return create(appliance);
    }

    public void deleteById(Long id) {
        appliances.removeIf(appliance -> appliance.getId().equals(id));
    }
}
