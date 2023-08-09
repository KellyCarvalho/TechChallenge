package br.com.fiap.techchallenge.person;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.*;

public record PersonForm(@NotNull @Size(min = 1, max = 255) String name,
                         Collection<RelatedPersonForm> relatedPersons,
                         @NotNull @Past LocalDate birthDate,
                         @NotNull Gender gender) {

    @Override
    public Collection<RelatedPersonForm> relatedPersons() {
        return Optional.ofNullable(relatedPersons).orElse(Set.of());
    }

    public Person toEntity() {
        return new Person(name, birthDate, gender);
    }
}
