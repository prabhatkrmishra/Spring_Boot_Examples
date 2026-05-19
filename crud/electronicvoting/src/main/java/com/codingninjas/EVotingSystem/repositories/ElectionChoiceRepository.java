package com.codingninjas.EVotingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.codingninjas.EVotingSystem.entities.ElectionChoice;

public interface ElectionChoiceRepository extends JpaRepository<ElectionChoice, Long> {

    @Query("SELECT COUNT(ec) FROM ElectionChoice ec WHERE ec.election.id = :electionId")
    long countByElectionId(@Param("electionId") Long electionId);
}