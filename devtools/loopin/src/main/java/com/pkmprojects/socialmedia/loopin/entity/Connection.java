package com.pkmprojects.socialmedia.loopin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "connections", schema = "loopin_schema")
@Getter
@Setter
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String emailId;

    private String company;

    private String username;

    private String level;
}
