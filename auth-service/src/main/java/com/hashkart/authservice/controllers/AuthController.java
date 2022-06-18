package com.hashkart.authservice.controllers;


import com.hashkart.authservice.entities.AuthRequest;
import com.hashkart.authservice.entities.AuthResponse;
import com.hashkart.authservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.register(authRequest));
    }

    @GetMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestParam("name") String name, @RequestParam("password") String password) {
        try {
            return ResponseEntity.ok(authService.login(name, password));
        }catch (CredentialException ex){
            return new ResponseEntity<>("User name or password incorrect", HttpStatus.UNAUTHORIZED);
        }
    }

}
