package br.com.fiap.techchallenge.person.relatedPerson;

import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class RelatedPersonId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private Long relatedPersonId;

    public RelatedPersonId() {}

    public RelatedPersonId(Long userId, Long relatedPersonId) {
        super();
        this.userId = userId;
        this.relatedPersonId = relatedPersonId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRelatedPersonId() {
        return relatedPersonId;
    }

    public void setRelatedPersonId(Long relatedPersonId) {
        this.relatedPersonId = relatedPersonId;
    }
}
