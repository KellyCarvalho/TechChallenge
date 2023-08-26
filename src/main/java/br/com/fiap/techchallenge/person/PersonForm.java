package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.person.relatedPerson.Connection;
import br.com.fiap.techchallenge.person.relatedPerson.RelatedPerson;
import br.com.fiap.techchallenge.user.User;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PersonForm(@NotNull @Size(min = 1, max = 255) String name,
                         @NotNull Long userId,
                         @NotNull Connection userConnection,
                         @NotNull @Past LocalDate birthDate,
                         @NotNull Gender gender) {

    public RelatedPerson relatedPersonEntity(User user, Person person) {
        return new RelatedPerson(user, person, userConnection);
    }

    public Person toEntity() {
        return new Person(name, birthDate, gender);
    }
}
