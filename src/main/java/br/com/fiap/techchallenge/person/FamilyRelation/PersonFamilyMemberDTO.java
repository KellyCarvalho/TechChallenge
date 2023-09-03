package br.com.fiap.techchallenge.person.FamilyRelation;

import br.com.fiap.techchallenge.person.Gender;
import br.com.fiap.techchallenge.person.PersonSimpleView;

import java.time.LocalDate;

public class PersonFamilyMemberDTO {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private int depth;
    private String relation;

    public PersonFamilyMemberDTO(PersonFamilyMember personFamilyMember) {
        this.id = personFamilyMember.getId();
        this.name = personFamilyMember.getName();
        this.birthDate = personFamilyMember.getBirthDate();
        this.gender = personFamilyMember.getGender();
        this.depth = personFamilyMember.getDepth();
    }

    public PersonSimpleView personSimpleView() {
        return new PersonSimpleView(getId(), getName(), getBirthDate(), getGender());
    }

    public void updateRelationForDescendant() {
        this.relation = RelationHelper.getRelationForDescendant(depth);
    }

    public void updateRelationForBrother() {
        this.relation = RelationHelper.getRelationForBrother();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public int getDepth() {
        return depth;
    }

    public String getRelation() {
        return relation;
    }
}
