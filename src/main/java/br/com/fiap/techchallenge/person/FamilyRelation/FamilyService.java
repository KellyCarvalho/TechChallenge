package br.com.fiap.techchallenge.person.FamilyRelation;

import br.com.fiap.techchallenge.person.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyService {

    private final ClosureTableRepository closureTableRepository;
    private final PersonRepository personRepository;

    public FamilyService(ClosureTableRepository closureTableRepository, PersonRepository personRepository) {
        this.closureTableRepository = closureTableRepository;
        this.personRepository = personRepository;
    }

    public void addChild(Person parent, Person child) {
        closureTableRepository.insert(parent.getId(), child.getId());
    }

//    public List<Person> getChildren(Person parent) {
//        List<Long> descendantIds = closureTableRepository.findDescendantsIds(parent.getId());
//        return personRepository.findAllById(descendantIds);
//    }
//
//    public List<Person> getAncestors(Person child) {
//        List<Long> ancestorIds = closureTableRepository.findAncestorsIds(child.getId());
//        return personRepository.findAllById(ancestorIds);
//    }

    public List<PersonFamilyMember> findBrothers(Long personId) {
        return closureTableRepository.findBrothers(personId);
    }

    public List<PersonFamilyMember> findChildren(Long personId) {
        return closureTableRepository.findChildren(personId);
    }
}
