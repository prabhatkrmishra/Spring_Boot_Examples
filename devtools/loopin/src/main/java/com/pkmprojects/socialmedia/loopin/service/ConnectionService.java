package com.pkmprojects.socialmedia.loopin.service;

import com.pkmprojects.socialmedia.loopin.repository.ConnectionRepository;
import com.pkmprojects.socialmedia.loopin.dto.ConnectionResponseDto;
import com.pkmprojects.socialmedia.loopin.entity.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConnectionService {

    @Autowired
    private ConnectionRepository connectionRepository;

    public List<ConnectionResponseDto> getAllConnections() {
        return connectionRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ConnectionResponseDto addConnection(Connection connection) {
        Connection saved = connectionRepository.save(connection);
        return mapToDto(saved);
    }

    public List<ConnectionResponseDto> getConnectionsByCompany(String company) {
        return connectionRepository.findByCompany(company)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ConnectionResponseDto mapToDto(Connection connection) {
        ConnectionResponseDto dto = new ConnectionResponseDto();
        dto.setName(connection.getName());
        dto.setEmailId(connection.getEmailId());
        dto.setCompany(connection.getCompany());
        dto.setUsername(connection.getUsername());
        dto.setLevel(connection.getLevel());
        return dto;
    }
}
