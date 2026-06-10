package com.pkmprojects.socialmedia.loopin.controller;

import com.pkmprojects.socialmedia.loopin.dto.ConnectionResponseDto;
import com.pkmprojects.socialmedia.loopin.entity.Connection;
import com.pkmprojects.socialmedia.loopin.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ConnectionService connectionService;

    @GetMapping("/connections")
    public List<ConnectionResponseDto> getAllConnections() {
        return connectionService.getAllConnections();
    }

    @PostMapping("/add")
    public ConnectionResponseDto addConnection(@RequestBody Connection connection) {
        return connectionService.addConnection(connection);
    }

    @GetMapping("/connections/{company}")
    public List<ConnectionResponseDto> getConnectionsByCompany(@PathVariable String company) {
        return connectionService.getConnectionsByCompany(company);
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('USER')")
    public String getTestStatus(){
        return "Test executed successfuly";
    }
}
