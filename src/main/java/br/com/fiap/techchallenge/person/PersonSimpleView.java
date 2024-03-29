package br.com.fiap.techchallenge.person;

import java.time.LocalDate;
import java.util.Collection;

public record PersonSimpleView(Long id, String name, LocalDate birthDate, Gender gender) {

    public PersonSimpleView(Person person) {
        this(person.getId(), person.getName(), person.getBirthDate(), person.getGender());
    }

    public static Collection<PersonSimpleView> fromList(Collection<Person> people) {
        return people.stream().map(PersonSimpleView::new).toList();
    }
}
