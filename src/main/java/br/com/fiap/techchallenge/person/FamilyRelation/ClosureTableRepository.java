package br.com.fiap.techchallenge.person.FamilyRelation;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClosureTableRepository extends JpaRepository<ClosureTable, Long> {

    @Transactional
    @Modifying
    @Query(value = """
        INSERT IGNORE INTO closure_table (ancestor_id, descendant_id, depth)
        SELECT p.ancestor_id, :newDescendantId, p.depth + 1
            FROM closure_table AS p
        WHERE p.descendant_id = :parentNodeId
        UNION ALL
        SELECT :newDescendantId, :newDescendantId, 0
    """, nativeQuery = true)
    void insert(@Param("parentNodeId") Long parentNodeId, @Param("newDescendantId") Long newDescendantId);

    @Transactional
    @Modifying
    @Query(value = """
        DELETE FROM closure_table WHERE ancestor_id = :personId OR descendant_id = :personId
    """, nativeQuery = true)
    void deleteAllRelations(@Param("personId") Long personId);

    @Query(value = """
    WITH Ancestors AS (
        SELECT ancestor_id, depth FROM closure_table WHERE descendant_id = :descendantId AND depth <> 0
    ) SELECT p.id, p.name, p.birth_date AS birthDate, p.gender, a.depth FROM Ancestors a JOIN person p ON a.ancestor_id = p.id
    """, nativeQuery = true)
    List<PersonFamilyMember> findParents(@Param("descendantId") Long descendantId);

    @Query(value = """
    WITH Descentants AS (
        SELECT descendant_id, depth FROM closure_table WHERE ancestor_id = :ancestorId AND depth <> 0
    ) SELECT p.id, p.name, p.birth_date AS birthDate, p.gender, d.depth FROM Descentants d JOIN person p ON d.descendant_id = p.id
    """, nativeQuery = true)
    List<PersonFamilyMember> findChildren(@Param("ancestorId") Long ancestorId);

    @Query(value = """
    WITH Ancestor AS (
        SELECT ancestor_id AS id, depth FROM closure_table WHERE descendant_id = :personId AND depth = 1 LIMIT 1
    ), Brothers AS (
        SELECT ct.descendant_id, ct.depth FROM closure_table ct JOIN Ancestor a ON ct.ancestor_id = a.id WHERE ct.descendant_id <> :personId AND ct.depth = 1
    ) SELECT p.id, p.name, p.birth_date AS birthDate, p.gender, b.depth FROM Brothers b JOIN person p ON b.descendant_id = p.id
    """, nativeQuery = true)
    List<PersonFamilyMember> findBrothers(@Param("personId") Long personId);
}