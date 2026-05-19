package com.codingninjas.EVotingSystem.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    @JsonIgnoreProperties({"vote"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "election_id")
    @JsonIgnoreProperties({"electionChoices", "votes"})
    private Election election;

    @ManyToOne
    @JoinColumn(name = "election_choice_id")
    @JsonIgnoreProperties({"election", "votes"})
    private ElectionChoice electionChoice;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public ElectionChoice getElectionChoice() {
        return electionChoice;
    }

    public void setElectionChoice(ElectionChoice electionChoice) {
        this.electionChoice = electionChoice;
    }
}