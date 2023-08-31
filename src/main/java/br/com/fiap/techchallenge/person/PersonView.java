package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.address.AddressSimpleView;
import br.com.fiap.techchallenge.address.AddressView;
import br.com.fiap.techchallenge.user.UserView;

import java.time.LocalDate;
import java.util.Collection;

public record PersonView(Long id, String name, LocalDate birthDate, Gender gender, Connection connectionToUser, UserView relatedUser, Collection<AddressSimpleView> addresses) {

    public PersonView(Person person) {
        this(person.getId(), person.getName(), person.getBirthDate(), person.getGender(), person.getConnectionToUser(), new UserView(person.getUser()), AddressSimpleView.fromList(person.getAddresses()));
    }

    public static Collection<PersonView> fromList(Collection<Person> people) {
        return people.stream().map(PersonView::new).toList();
    }
}
