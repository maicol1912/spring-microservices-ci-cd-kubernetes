package com.maicol1912.identityService.controller;

import com.maicol1912.identityService.dto.AuthRequest;
import com.maicol1912.identityService.entity.UserCredentials;
import com.maicol1912.identityService.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService service;
    private AuthenticationManager authenticationManager;
    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredentials user){
        return service.saveUser(user);
    }
    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()) {
            System.out.println("Ingrese en el primer condicional");
            return service.generateToken(authRequest.getUsername());
        }else {
            System.out.println("Ingrese en el else");
            throw new RuntimeException("Invalid Access");
        }
    }

    @GetMapping("/validate")
    public String getToken(@RequestParam("token") String token){
        service.validateToken(token);
        return "Token is valid";
    }
}
