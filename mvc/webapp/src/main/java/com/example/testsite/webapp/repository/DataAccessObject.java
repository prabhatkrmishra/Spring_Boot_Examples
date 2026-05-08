package com.example.testsite.webapp.repository;

import java.util.Optional;

public interface DataAccessObject<T> {
    public Optional<T> getUser(Integer id);
    public int saveUser(T user);
}
