package com.CN.Gym.controller;

import com.CN.Gym.dto.GymDto;
import com.CN.Gym.model.Gym;
import com.CN.Gym.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gym")
public class GymController {

    @Autowired
    private GymService gymService;

    // ADMIN: Get all gyms
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<Gym> getAllGyms() {
        return gymService.getAllGyms();
    }

    // ADMIN: Get gym by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Gym getGymById(@PathVariable Long id) {
        return gymService.getGymById(id);
    }

    // ADMIN: Create a new gym
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createGym(@RequestBody GymDto gymDto) {
        gymService.createGym(gymDto);
    }

    // ADMIN: Update gym
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void updateGym(@RequestBody GymDto gymDto, @PathVariable Long id) {
        gymService.updateGym(gymDto, id);
    }

    // ADMIN: Delete gym
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGym(@PathVariable Long id) {
        gymService.deleteGymById(id);
    }

    // ADMIN: Add a member to a gym
    @PostMapping("/addMember")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMember(@RequestParam Long userId, @RequestParam Long gymId) {
        gymService.addMember(userId, gymId);
    }

    // ADMIN: Remove a member from a gym
    @DeleteMapping("/deleteMember")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMember(@RequestParam("userId") Long userId, @RequestParam("gymId") Long gymId) {
        gymService.deleteMember(userId, gymId);
    }
}