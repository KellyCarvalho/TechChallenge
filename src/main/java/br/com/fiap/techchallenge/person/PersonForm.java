package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.address.Address;
import br.com.fiap.techchallenge.user.User;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record PersonForm(@NotNull @Size(min = 1, max = 255) String name,
                         @NotNull Long userId,
                         @NotNull Connection connectionToUser,
                         @NotNull @Past LocalDate birthDate,
                         @NotNull Gender gender,
                         @NotEmpty List<Long> addressesIds) {

    public Person toEntity(User user, List<Address> addresses) {
        return new Person(name, birthDate, gender, user, connectionToUser, addresses);
    }
}
