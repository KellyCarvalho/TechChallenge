package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.user.UserView;

import java.time.LocalDate;
import java.util.Collection;

public record PersonView(Long id, String name, LocalDate birthDate, Gender gender, Connection connectionToUser, UserView relatedUser) {
    public PersonView(Person person) {
        this(person.getId(), person.getName(), person.getBirthDate(), person.getGender(), person.getConnectionToUser(), new UserView(person.getUser()));
    }

    public static Collection<PersonView> toPersonViewList(Collection<Person> people) {
        return people.stream().map(PersonView::new).toList();
    }
}
