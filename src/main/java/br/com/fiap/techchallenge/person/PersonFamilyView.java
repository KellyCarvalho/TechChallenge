package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.person.FamilyRelation.PersonFamilyMember;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public record PersonFamilyView(Long id, String name, LocalDate birthDate, Gender gender, Map<Integer, PersonSimpleView> familyMembers) {
    public PersonFamilyView(Person person, Collection<PersonFamilyMember> familyMembers) {
        this(person.getId(), person.getName(), person.getBirthDate(), person.getGender(), familyMembers.stream().collect(Collectors.toMap(PersonFamilyMember::getDepth, PersonFamilyMember::personSimpleView)));
    }
}
