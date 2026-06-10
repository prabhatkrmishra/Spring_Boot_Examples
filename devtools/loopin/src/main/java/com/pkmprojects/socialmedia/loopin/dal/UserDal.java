package com.pkmprojects.socialmedia.loopin.dal;

import com.pkmprojects.socialmedia.loopin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDal extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
