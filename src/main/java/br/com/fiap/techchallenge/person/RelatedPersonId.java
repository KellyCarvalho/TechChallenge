package br.com.fiap.techchallenge.person;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RelatedPersonId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long personId;
    private Long relatedPersonId;

    public RelatedPersonId() {}

    public RelatedPersonId(Long personId, Long relatedPersonId) {
        super();
        this.personId = personId;
        this.relatedPersonId = relatedPersonId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getRelatedPersonId() {
        return relatedPersonId;
    }

    public void setRelatedPersonId(Long relatedPersonId) {
        this.relatedPersonId = relatedPersonId;
    }
}
