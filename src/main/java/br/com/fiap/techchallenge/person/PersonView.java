package br.com.fiap.techchallenge.person;

import java.time.LocalDate;

public record PersonView(Long id, String name, LocalDate birthDate, Gender gender) {
    public PersonView(Person person) {
        this(person.getId(), person.getName(), person.getBirthDate(), person.getGender());
    }
}
