package br.com.fiap.techchallenge.address;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AddressRepository {

    static private final Set<Address> addresses = new LinkedHashSet<>();
    private static Long idAutoincrement = 1L;

    public Address save(Address address) {
        address.setId(idAutoincrement++);
        addresses.add(address);
        return address;
    }

    public Optional<Address> findById(Long id){
        return addresses.stream().filter(address -> address.getId().equals(id)).findFirst();
    }

    public Collection<Address> findAll(){
        return List.copyOf(addresses);
    }

    public void deleteById(Long id){
        addresses.removeIf(address -> address.getId().equals(id));
    }
}
