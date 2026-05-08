package com.example.testsite.webapp.services;

import com.example.testsite.webapp.domain.User;

public interface UserService {
    public User getUser();
    public boolean signUp(String name, String gender, String location, String college);
}
