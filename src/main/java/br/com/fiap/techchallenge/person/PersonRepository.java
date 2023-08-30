package br.com.fiap.techchallenge.person;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryCustom {
}