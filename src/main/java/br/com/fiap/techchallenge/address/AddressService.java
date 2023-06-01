package br.com.fiap.techchallenge.address;

import br.com.fiap.techchallenge.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressDTO update(Long id, AddressDTO addressDTO){
        Address address = addressRepository.findById(id).orElseThrow(() -> new NotFoundException("Endereço não encontrado"));
        address.setCity(addressDTO.city());
        address.setNeighborhood(addressDTO.neighborhood());
        address.setState(addressDTO.state());
        address.setStreet(addressDTO.street());
        address.setNumber(addressDTO.number());
        addressRepository.save(address);
        return new AddressDTO(address);
    }

    public AddressDTO findById(Long id){
        Address address = addressRepository.findById(id).orElseThrow(() -> new NotFoundException("Endereço não encontrado"));
        return new AddressDTO(address);
    }

}
