package com.CodingNinjas.LeaveXpress.service;

import com.CodingNinjas.LeaveXpress.dto.LeaveDto;
import com.CodingNinjas.LeaveXpress.exception.LeaveNotFoundException;
import com.CodingNinjas.LeaveXpress.model.LeaveModel;
import com.CodingNinjas.LeaveXpress.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    // Get leave by ID
    public LeaveModel getLeaveById(Long id) {
        return leaveRepository.findById(id)
                .orElseThrow(() -> new LeaveNotFoundException(id));
    }

    // Get all leaves
    public List<LeaveModel> getAllLeaves() {
        return leaveRepository.findAll();
    }

    // Get all accepted leaves
    public List<LeaveModel> getAcceptedLeaves() {
        return leaveRepository.findByIsAccepted(true);
    }

    // Get all rejected leaves
    public List<LeaveModel> getRejectedLeaves() {
        return leaveRepository.findByIsAccepted(false);
    }

    // Get leave status by ID - Returns boolean (true for accepted, false for rejected)
    public boolean getLeaveStatus(Long id) {
        LeaveModel leave = getLeaveById(id);
        return leave.isAccepted();  // Return the boolean status directly
    }

    // Apply for leave
    public LeaveModel applyForLeave(LeaveDto leaveDto) {
        LeaveModel leave = new LeaveModel(
                leaveDto.getType(),
                leaveDto.getStartDate(),
                leaveDto.getEndDate(),
                leaveDto.getDescription(),
                false // Initially not accepted
        );
        return leaveRepository.save(leave);
    }

    // Update leave
    public LeaveModel updateLeave(Long id, LeaveDto updatedLeave) {
        LeaveModel existingLeave = getLeaveById(id);
        existingLeave.setType(updatedLeave.getType());
        existingLeave.setStartDate(updatedLeave.getStartDate());
        existingLeave.setEndDate(updatedLeave.getEndDate());
        existingLeave.setDescription(updatedLeave.getDescription());
        return leaveRepository.save(existingLeave);
    }

    // Delete leave
    public void deleteLeave(Long id) {
        LeaveModel leave = getLeaveById(id);
        leaveRepository.delete(leave);
    }

    // Accept leave (Manager only)
    public LeaveModel acceptLeave(Long id) {
        LeaveModel leave = getLeaveById(id);
        leave.setAccepted(true);
        return leaveRepository.save(leave);
    }

    // Reject leave (Manager only)
    public LeaveModel rejectLeave(Long id) {
        LeaveModel leave = getLeaveById(id);
        leave.setAccepted(false);
        return leaveRepository.save(leave);
    }
}