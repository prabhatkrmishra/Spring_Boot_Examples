package com.CodingNinjas.LeaveXpress.controller;

import com.CodingNinjas.LeaveXpress.dto.LeaveDto;
import com.CodingNinjas.LeaveXpress.model.LeaveModel;
import com.CodingNinjas.LeaveXpress.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    // GET "/api/leave/{id}"
    @GetMapping("/{id}")
    public ResponseEntity<LeaveModel> getLeaveById(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.getLeaveById(id));
    }

    // GET "/api/leave/all"
    @GetMapping("/all")
    public ResponseEntity<List<LeaveModel>> getAllLeaves() {
        return ResponseEntity.ok(leaveService.getAllLeaves());
    }

    // GET "/api/leave/accepted"
    @GetMapping("/accepted")
    public ResponseEntity<List<LeaveModel>> getAcceptedLeaves() {
        return ResponseEntity.ok(leaveService.getAcceptedLeaves());
    }

    // GET "/api/leave/rejected"
    @GetMapping("/rejected")
    public ResponseEntity<List<LeaveModel>> getRejectedLeaves() {
        return ResponseEntity.ok(leaveService.getRejectedLeaves());
    }

    // GET "/api/leave/status/{id}" - Returns boolean status
    @GetMapping("/status/{id}")
    public ResponseEntity<Boolean> getLeaveStatus(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.getLeaveStatus(id));
    }

    // PUT "/api/leave/{id}"
    @PutMapping("/{id}")
    public ResponseEntity<LeaveModel> updateLeave(@PathVariable Long id, @RequestBody LeaveDto updatedLeave) {
        return ResponseEntity.ok(leaveService.updateLeave(id, updatedLeave));
    }

    // DELETE "/api/leave/{id}"
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
        return ResponseEntity.ok().build();
    }

    // POST "/api/leave/apply"
    @PostMapping("/apply")
    public ResponseEntity<LeaveModel> applyForLeave(@RequestBody LeaveDto leaveRequest) {
        return ResponseEntity.ok(leaveService.applyForLeave(leaveRequest));
    }

    // POST "/api/leave/accept/{id}" - Manager only
    @PostMapping("/accept/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<LeaveModel> acceptLeave(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(leaveService.acceptLeave(id));
    }

    // POST "/api/leave/reject/{id}" - Manager only
    @PostMapping("/reject/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<LeaveModel> rejectLeave(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.rejectLeave(id));
    }
}