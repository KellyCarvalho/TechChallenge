package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.user.UserView;

import java.time.LocalDate;

public record PersonView(Long id, String name, LocalDate birthDate, Gender gender, Connection connectionToUser, UserView relatedUser) {
    public PersonView(Person person) {
        this(person.getId(), person.getName(), person.getBirthDate(), person.getGender(), person.getConnectionToUser(), new UserView(person.getUser()));
    }
}
