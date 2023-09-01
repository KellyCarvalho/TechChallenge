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
//        ClosureTable closureTable = new ClosureTable();
//        closureTable.setAncestor(parent);
//        closureTable.setDescendant(child);
//        closureTable.setDepth(0);

//        closureTableRepository.save(closureTable);
        closureTableRepository.insert(parent.getId(), child.getId());
    }

    public List<Person> getChildren(Person parent) {
        List<Long> descendantIds = closureTableRepository.findDescendantsIds(parent.getId());
        return personRepository.findAllById(descendantIds);
    }

    public List<Person> getAncestors(Person child) {
        List<Long> ancestorIds = closureTableRepository.findAncestorsIds(child.getId());
        return personRepository.findAllById(ancestorIds);
    }

    public List<PersonFamilyMember> findChildren(Long id) {
        return closureTableRepository.findChildren(id);
    }
}
