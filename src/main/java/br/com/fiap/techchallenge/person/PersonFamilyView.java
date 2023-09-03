package br.com.fiap.techchallenge.person;

import br.com.fiap.techchallenge.person.FamilyRelation.PersonFamilyMemberDTO;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public record PersonFamilyView(Long id, String name, LocalDate birthDate, Gender gender, Map<String, List<PersonSimpleView>> familyMembers) {
    public PersonFamilyView(Person person, Collection<PersonFamilyMemberDTO> familyMembers) {
        this(person.getId(), person.getName(), person.getBirthDate(), person.getGender(), getFamilyMembersMap(familyMembers));
    }

    private static Map<String, List<PersonSimpleView>> getFamilyMembersMap(Collection<PersonFamilyMemberDTO> familyMembers) {
        return familyMembers.stream()
                .collect(Collectors.groupingBy(PersonFamilyMemberDTO::getRelation, Collectors.mapping(PersonFamilyMemberDTO::personSimpleView, Collectors.toList())));
    }
}
