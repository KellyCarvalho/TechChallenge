package br.com.fiap.techchallenge.appliance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {
}
