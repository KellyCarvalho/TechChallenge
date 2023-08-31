package br.com.fiap.techchallenge.person.FamilyRelation;

import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class FamilyRelationId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long personId;
    private Long familyMemberId;

    public FamilyRelationId() {
    }

    public FamilyRelationId(Long personId, Long familyMemberId) {
        this.personId = personId;
        this.familyMemberId = familyMemberId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(Long familyMemberId) {
        this.familyMemberId = familyMemberId;
    }
}
