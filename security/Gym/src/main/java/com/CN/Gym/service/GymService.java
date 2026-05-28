package com.CN.Gym.service;

import com.CN.Gym.dto.GymDto;
import com.CN.Gym.exception.GymNotFoundException;
import com.CN.Gym.exception.UserNotFoundException;
import com.CN.Gym.model.Gym;
import com.CN.Gym.model.User;
import com.CN.Gym.repository.GymRepository;
import com.CN.Gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GymService {

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Gym> getAllGyms() {
        return gymRepository.findAll();
    }

    public Gym getGymById(Long id) {
        return gymRepository.findById(id)
                .orElseThrow(() -> new GymNotFoundException("Gym not found with id: " + id));
    }

    public void deleteGymById(Long id) {
        if (!gymRepository.existsById(id)) {
            throw new GymNotFoundException("Gym not found with id: " + id);
        }
        gymRepository.deleteById(id);
    }

    public void updateGym(GymDto gymDto, Long id) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new GymNotFoundException("Gym not found with id: " + id));
        gym.setName(gymDto.getName());
        gym.setAddress(gymDto.getAddress());
        gym.setContactNo(gymDto.getContactNo());
        gym.setMembershipPlans(gymDto.getMembershipPlans());
        gym.setFacilities(gymDto.getFacilities());
        gymRepository.save(gym);
    }

    public void createGym(GymDto gymDto) {
        Gym gym = Gym.builder()
                .name(gymDto.getName())
                .address(gymDto.getAddress())
                .contactNo(gymDto.getContactNo())
                .membershipPlans(gymDto.getMembershipPlans())
                .facilities(gymDto.getFacilities())
                .members(new ArrayList<>())
                .build();
        gymRepository.save(gym);
    }

    public void addMember(Long userId, Long gymId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        Gym gym = gymRepository.findById(gymId)
                .orElseThrow(() -> new GymNotFoundException("Gym not found with id: " + gymId));
        user.setGym(gym);
        userRepository.save(user);
    }

    public void deleteMember(Long userId, Long gymId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        gymRepository.findById(gymId)
                .orElseThrow(() -> new GymNotFoundException("Gym not found with id: " + gymId));
        if (user.getGym() != null && user.getGym().getId().equals(gymId)) {
            user.setGym(null);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User is not a member of this gym");
        }
    }
}