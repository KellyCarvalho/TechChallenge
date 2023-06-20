package br.com.fiap.techchallenge.person;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PersonRepository {

    private static Set<Person> people = new LinkedHashSet<>();

    private static Long idAutoincrement = 1L;

    public Collection<Person> findAll() {
        return Collections.unmodifiableCollection(people);
    }

    public Optional<Person> findById(Long id) {
        return people.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Person save(Person p) {
        p.setId(idAutoincrement++);
        people.add(p);
        return p;
    }

    public void delete(Long id) {
        people.removeIf(p -> p.getId().equals(id));
    }
}