package br.com.fiap.techchallenge.address;


public record AddressSearchForm(Long personId, String street, String number, String neighborhood, String city, String state) {
}
