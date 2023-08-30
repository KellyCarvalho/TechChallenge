package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.person.relatedPerson.Connection;
import br.com.fiap.techchallenge.person.relatedPerson.RelatedPerson;
import br.com.fiap.techchallenge.user.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PersonSearchForm(String name, Long userId, LocalDate birthDate, Gender gender) {
}
