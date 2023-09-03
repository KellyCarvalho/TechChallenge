package br.com.fiap.techchallenge.person.FamilyRelation;

import br.com.fiap.techchallenge.person.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyService {

    private final ClosureTableRepository closureTableRepository;

    public FamilyService(ClosureTableRepository closureTableRepository) {
        this.closureTableRepository = closureTableRepository;
    }

    public void addChild(Person parent, Person child) {
        closureTableRepository.insert(parent.getId(), child.getId());
    }

    public List<PersonFamilyMember> findParents(Long personId) {
        return closureTableRepository.findParents(personId);
    }

    public List<PersonFamilyMember> findChildren(Long personId) {
        return closureTableRepository.findChildren(personId);
    }

    public List<PersonFamilyMember> findBrothers(Long personId) {
        return closureTableRepository.findBrothers(personId);
    }
}
