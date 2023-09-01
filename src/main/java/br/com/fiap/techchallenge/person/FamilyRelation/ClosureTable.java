package br.com.fiap.techchallenge.person.FamilyRelation;

import br.com.fiap.techchallenge.person.Person;
import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueNumberAndStatus", columnNames = { "ancestor_id", "descendant_id" }) })
public class ClosureTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ancestor_id")
    private Person ancestor;


    @ManyToOne
    @JoinColumn(name = "descendant_id")
    private Person descendant;

    private int depth;

    public ClosureTable() {}

    public Person getAncestor() {
        return ancestor;
    }

    public void setAncestor(Person ancestor) {
        this.ancestor = ancestor;
    }

    public Person getDescendant() {
        return descendant;
    }

    public void setDescendant(Person descendant) {
        this.descendant = descendant;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}