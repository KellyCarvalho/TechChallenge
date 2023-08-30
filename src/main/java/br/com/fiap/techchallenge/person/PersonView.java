package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.user.UserView;

import java.time.LocalDate;

public record PersonView(Long id, String name, LocalDate birthDate, Gender gender, Connection userConnection, UserView relatedUser) {
    public PersonView(Person person) {
        this(person.getId(), person.getName(), person.getBirthDate(), person.getGender(), person.getUserConnection(), new UserView(person.getUser()));
    }
}
