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
        SELECT :newDescendantId, :newDescendantId, 0;
    """, nativeQuery = true)
    void insert(@Param("parentNodeId") Long parentNodeId, @Param("newDescendantId") Long newDescendantId);

    @Query(value = "SELECT ancestor_id FROM closure_table WHERE descendant_id = :descendantId", nativeQuery = true)
    List<Long> findAncestorsIds(@Param("descendantId") Long descendantId);

    @Query(value = "SELECT descendant_id FROM closure_table WHERE ancestor_id = :ancestorId", nativeQuery = true)
    List<Long> findDescendantsIds(@Param("ancestorId") Long ancestorId);

    @Query(value = """
    WITH Ancestor AS (
        SELECT descendant_id AS id, depth FROM closure_table WHERE descendant_id = :descendantId ORDER BY depth DESC LIMIT 1
    ), Descentants AS (
        SELECT ct.descendant_id, ct.depth FROM closure_table ct JOIN Ancestor a ON ct.ancestor_id = a.id WHERE ancestor_id = :descendantId AND ct.depth <> 0
    ) SELECT p.id, p.name, p.birth_date AS birthDate, p.gender, d.depth FROM Descentants d JOIN person p ON d.descendant_id = p.id;
    """, nativeQuery = true)
    List<PersonFamilyMember> findChildren(@Param("descendantId") Long descendantId);

    @Query(value = """
    WITH Ancestor AS (
        SELECT descendant_id AS id, depth FROM closure_table WHERE descendant_id = :descendantId ORDER BY depth DESC LIMIT 1
    ), Descentants AS (
        SELECT ct.descendant_id, ct.depth FROM closure_table ct JOIN Ancestor a ON ct.ancestor_id = a.id WHERE ancestor_id = :descendantId AND ct.depth BETWEEN 1 AND :maxDepth
    ) SELECT p.id, p.name, p.birth_date AS birthDate, p.gender, d.depth FROM Descentants d JOIN person p ON d.descendant_id = p.id;
    """, nativeQuery = true)
    List<PersonFamilyMember> findChildren(@Param("descendantId") Long descendantId, @Param("maxDepth") int maxDepth);

    @Query(value = """
    WITH Ancestor AS (
        SELECT ancestor_id AS id, depth FROM closure_table WHERE descendant_id = :personId AND depth = 1 LIMIT 1
    ), Brothers AS (
        SELECT ct.descendant_id, ct.depth FROM closure_table ct JOIN Ancestor a ON ct.ancestor_id = a.id WHERE ct.descendant_id <> :personId AND ct.depth = 1
    ) SELECT p.id, p.name, p.birth_date AS birthDate, p.gender, b.depth FROM Brothers b JOIN person p ON b.descendant_id = p.id;
    """, nativeQuery = true)
    List<PersonFamilyMember> findBrothers(@Param("personId") Long personId);
}