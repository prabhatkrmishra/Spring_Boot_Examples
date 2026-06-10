package com.pkmprojects.socialmedia.loopin.dal;

import com.pkmprojects.socialmedia.loopin.entity.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionDal extends JpaRepository<Connection, Integer> {

    List<Connection> findByCompany(String company);
}
