package com.CN.Gym.service;

import com.CN.Gym.dto.UserRequest;
import com.CN.Gym.dto.WorkoutDto;
import com.CN.Gym.exception.UserNotFoundException;
import com.CN.Gym.model.Role;
import com.CN.Gym.model.User;
import com.CN.Gym.model.Workout;
import com.CN.Gym.repository.UserRepository;
import com.CN.Gym.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(UserRequest userRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userRequest.getPassword());
        User user = User.builder()
                .email(userRequest.getEmail())
                .age(userRequest.getAge())
                .gender(userRequest.getGender())
                .password(encodedPassword)
                .build();

        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        if (userRequest.getUserType() != null) {
            if (userRequest.getUserType().equalsIgnoreCase("TRAINER")) {
                role.setRoleName("ROLE_TRAINER");
            } else if (userRequest.getUserType().equalsIgnoreCase("ADMIN")) {
                role.setRoleName("ROLE_ADMIN");
            } else {
                role.setRoleName("ROLE_CUSTOMER");
            }
        } else {
            role.setRoleName("ROLE_CUSTOMER");
        }
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public void updateUser(UserRequest userRequest, Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setAge(userRequest.getAge());
        existingUser.setGender(userRequest.getGender());
        if (userRequest.getPassword() != null && !userRequest.getPassword().isEmpty()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            existingUser.setPassword(encoder.encode(userRequest.getPassword()));
        }
        userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public void addWorkout(WorkoutDto workoutDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        Workout workout = Workout.builder()
                .workoutName(workoutDto.getWorkoutName())
                .description(workoutDto.getDescription())
                .difficultyLevel(workoutDto.getDifficultyLevel())
                .duration(workoutDto.getDuration())
                .user(user)
                .build();
        workoutRepository.save(workout);
        user.getWorkouts().add(workout);
        userRepository.save(user);
    }
}