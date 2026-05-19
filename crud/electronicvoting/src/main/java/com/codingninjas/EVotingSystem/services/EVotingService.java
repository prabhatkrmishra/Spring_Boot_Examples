package com.codingninjas.EVotingSystem.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codingninjas.EVotingSystem.entities.Election;
import com.codingninjas.EVotingSystem.entities.ElectionChoice;
import com.codingninjas.EVotingSystem.entities.User;
import com.codingninjas.EVotingSystem.entities.Vote;
import com.codingninjas.EVotingSystem.repositories.ElectionChoiceRepository;
import com.codingninjas.EVotingSystem.repositories.ElectionRepository;
import com.codingninjas.EVotingSystem.repositories.UserRepository;
import com.codingninjas.EVotingSystem.repositories.VoteRepository;

@Service
public class EVotingService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ElectionRepository electionRepository;

    @Autowired
    ElectionChoiceRepository electionChoiceRepository;

    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addVote(Long userId, Long electionId, Long electionChoiceId) {
        // Check if user has already voted in this election
        if (AlreadyGivenVote(userId, electionId)) {
            throw new RuntimeException("You have already given your vote");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new RuntimeException("Election not found"));
        ElectionChoice electionChoice = electionChoiceRepository.findById(electionChoiceId)
                .orElseThrow(() -> new RuntimeException("Election choice not found"));

        Vote vote = new Vote();
        vote.setUser(user);
        vote.setElection(election);
        vote.setElectionChoice(electionChoice);

        voteRepository.save(vote);
    }

    public void addElection(Election election) {
        electionRepository.save(election);
    }

    public boolean AlreadyGivenVote(Long userId, Long electionId) {
        return voteRepository.countByElectionIdAndUserId(electionId, userId) > 0;
    }

    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }

    public void addElectionChoice(ElectionChoice electionChoice) {
        if (electionChoice.getElection() != null && electionChoice.getElection().getId() > 0) {
            Election managedElection = electionRepository.findById(electionChoice.getElection().getId())
                    .orElseThrow(() -> new RuntimeException("Election not found with id: " + electionChoice.getElection().getId()));
            electionChoice.setElection(managedElection);
        }
        electionChoiceRepository.save(electionChoice);
    }

    public List<ElectionChoice> getAllElectionChoices() {
        return electionChoiceRepository.findAll();
    }

    public Election findElectionByName(String electionName) {
        List<Election> elections = electionRepository.findAll();
        for (Election e : elections) {
            if (e.getName().equals(electionName)) {
                return e;
            }
        }
        return null;
    }

    public long countTotalVotes() {
        return voteRepository.count();
    }

    public long countVotesByElectionName(String electionName) {
        return voteRepository.countByElectionName(electionName);
    }

    public long choicesByElection(Long electionId) {
        return electionChoiceRepository.countByElectionId(electionId);
    }

    public ElectionChoice findElectionWinner(String electionName) {
        List<ElectionChoice> winners = voteRepository.findWinnerByElectionName(electionName);
        if (winners != null && !winners.isEmpty()) {
            return winners.get(0);
        }
        return null;
    }
}