package br.com.fiap.techchallenge.user;

import br.com.fiap.techchallenge.person.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserForm(@NotNull @Size(min = 1, max = 255) String name,
                       @NotNull @Past LocalDate birthDate,
                       @NotNull Gender gender) {

    public User toEntity() {
        return new User(name, birthDate, gender);
    }
}
