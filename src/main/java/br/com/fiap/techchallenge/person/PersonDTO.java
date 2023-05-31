package br.com.fiap.techchallenge.person;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PersonDTO(@NotNull @Size(min = 2, max = 255) String name,
                        @NotNull @Past LocalDate birthDate,
                        @NotNull Gender gender) {
    public Person toEntity() {
        return new Person(name, birthDate, gender);
    }
}
