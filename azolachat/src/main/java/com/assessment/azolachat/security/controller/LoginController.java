package com.assessment.azolachat.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody UsernamePassword usernamePassword){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                usernamePassword.getUsername(), usernamePassword.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Login successful.", HttpStatus.OK);
    }
}
