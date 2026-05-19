package com.codingninjas.EVotingSystem.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "election")
    @JsonIgnore
    private List<ElectionChoice> electionChoices;

    @OneToMany(mappedBy = "election")
    @JsonIgnore
    private List<Vote> votes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ElectionChoice> getElectionChoices() {
        return electionChoices;
    }

    public void setElectionChoices(List<ElectionChoice> electionChoices) {
        this.electionChoices = electionChoices;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}