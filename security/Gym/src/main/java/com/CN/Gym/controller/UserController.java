package com.CN.Gym.controller;

import com.CN.Gym.dto.UserRequest;
import com.CN.Gym.dto.WorkoutDto;
import com.CN.Gym.model.User;
import com.CN.Gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // ADMIN: Get all users
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // PUBLIC: Register a new user
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
    }

    // CUSTOMER: Get user by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // CUSTOMER: Update user
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody UserRequest userRequest, @PathVariable Long id) {
        userService.updateUser(userRequest, id);
    }

    // ADMIN: Delete user
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // TRAINER: Assign workout to a customer
    @PostMapping("/workout/{userId}")
    @PreAuthorize("hasRole('TRAINER')")
    @ResponseStatus(HttpStatus.CREATED)
    public void addWorkout(@RequestBody WorkoutDto workoutDto, @PathVariable Long userId) {
        userService.addWorkout(workoutDto, userId);
    }
}