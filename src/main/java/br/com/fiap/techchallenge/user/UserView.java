package br.com.fiap.techchallenge.user;

import br.com.fiap.techchallenge.person.Gender;

import java.time.LocalDate;

public record UserView(Long id, String name, LocalDate birthDate, Gender gender) {
    public UserView(User user) {
        this(user.getId(), user.getName(), user.getBirthDate(), user.getGender());
    }
}
