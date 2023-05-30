package br.com.fiap.techchallenge.appliance;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ApplianceCollectionRepository  {

    static private final Set<Appliance> appliances;

    static {
        appliances = new LinkedHashSet<>();

        Appliance fridge = new Appliance("Refrigerador smart", "LG", "UVNANO 611L", 200, Appliance.Voltage.ONE_HUNDRED_TWENTY_SEVEN);
        Appliance stove = new Appliance("Fogão 5 bocas", "Atlas", "B097NC4JWK", null, Appliance.Voltage.BIVOLT);
        Appliance microwave = new Appliance("Forno de microondas", "Midea", "MRAS22", 700, Appliance.Voltage.ONE_HUNDRED_TWENTY_SEVEN);
        Appliance electricOven = new Appliance("Forno Elétrico", "Fischer", "26817-58801", 1750, Appliance.Voltage.TWO_HUNDRED_AND_TWENTY);
        Appliance washingMachine = new Appliance("Lavadora de Roupas 11kg", "Midea", "", null, Appliance.Voltage.ONE_HUNDRED_TWENTY_SEVEN);
        Appliance dishWasher = new Appliance("Lava louças", "Britânia", "BLL10B", null, Appliance.Voltage.ONE_HUNDRED_TWENTY_SEVEN);
        Appliance washingTank = new Appliance("Tanquinho Semiautomático", "Colormaq", "LCS10", null, Appliance.Voltage.TWO_HUNDRED_AND_TWENTY);
        Appliance airPurifier = new Appliance("Depurador de ar", "Suggar", "DPS181PR", null, Appliance.Voltage.ONE_HUNDRED_TWENTY_SEVEN);

        saveAll(Arrays.asList(fridge, stove, microwave, electricOven, washingMachine, dishWasher, washingTank, airPurifier));
    }

    public Collection<Appliance> findAll() {
        return appliances;
    }

    public Optional<Appliance> findById(Long id) {
        return appliances.stream().filter(appliance -> appliance.getId().equals(id)).findFirst();
    }

    public static void save(Appliance appliance) {
        appliance.setId(appliances.size() + 1L);
        appliances.add(appliance);
    }

    private static void saveAll(List<Appliance> appliances) {
        appliances.forEach(ApplianceCollectionRepository::save);
    }

}
