package br.com.fiap.techchallenge.person;

public record PersonSearchForm(Long userId, String name, Connection connectionToUser, Gender gender) {
}
