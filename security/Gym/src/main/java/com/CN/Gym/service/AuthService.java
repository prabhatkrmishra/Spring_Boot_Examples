package com.CN.Gym.service;

import com.CN.Gym.dto.JwtRequest;
import com.CN.Gym.dto.JwtResponse;
import com.CN.Gym.jwt.JwtAuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAuthenticationHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    public JwtResponse login(JwtRequest jwtRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtRequest.getUsername(),
                        jwtRequest.getPassword()
                )
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtHelper.generateToken(userDetails);
        return new JwtResponse(token);
    }
}