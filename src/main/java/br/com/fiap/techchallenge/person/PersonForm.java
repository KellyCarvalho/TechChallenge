package br.com.fiap.techchallenge.person;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PersonForm(@NotNull @Size(min = 1, max = 255) String name,
                         @NotNull @Past LocalDate birthDate,
                         @NotNull Gender gender) {
    public Person toEntity() {
        return new Person(name, birthDate, gender);
    }
}
