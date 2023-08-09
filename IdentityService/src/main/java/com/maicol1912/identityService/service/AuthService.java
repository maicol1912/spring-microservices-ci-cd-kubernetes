package com.maicol1912.identityService.service;

import com.maicol1912.identityService.entity.UserCredentials;
import com.maicol1912.identityService.repository.UserCredentialsRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private UserCredentialsRepository userCredentialsRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    public String saveUser(UserCredentials userCredentials){
        userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
        userCredentialsRepository.save(userCredentials);
        return "User added successfully";
    }

    public String generateToken(String username){
        return jwtService.generateToken(username);
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }
}
