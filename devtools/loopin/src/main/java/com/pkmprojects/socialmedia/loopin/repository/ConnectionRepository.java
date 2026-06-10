package com.pkmprojects.socialmedia.loopin.repository;

import com.pkmprojects.socialmedia.loopin.entity.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Integer> {

    List<Connection> findByCompany(String company);
}
