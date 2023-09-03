package br.com.fiap.techchallenge.person.FamilyRelation;

import br.com.fiap.techchallenge.person.Gender;

import java.time.LocalDate;

public interface PersonFamilyMember {

    Long getId();

    String getName();

    LocalDate getBirthDate();

    Gender getGender();

    int getDepth();
}
