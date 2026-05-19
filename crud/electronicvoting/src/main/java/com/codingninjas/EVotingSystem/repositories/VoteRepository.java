package com.codingninjas.EVotingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.codingninjas.EVotingSystem.entities.ElectionChoice;
import com.codingninjas.EVotingSystem.entities.Vote;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.election.id = :electionId AND v.user.id = :userId")
    long countByElectionIdAndUserId(@Param("electionId") Long electionId, @Param("userId") Long userId);

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.election.name = :electionName")
    long countByElectionName(@Param("electionName") String electionName);

    @Query("SELECT v.electionChoice FROM Vote v WHERE v.election.name = :electionName GROUP BY v.electionChoice ORDER BY COUNT(v) DESC")
    List<ElectionChoice> findWinnerByElectionName(@Param("electionName") String electionName);
}