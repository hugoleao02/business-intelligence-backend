package com.Business_Intelligence.bi_system_backend.controllers;

import com.Business_Intelligence.bi_system_backend.dtos.LoginResponse;
import com.Business_Intelligence.bi_system_backend.dtos.LoginUserDto;
import com.Business_Intelligence.bi_system_backend.dtos.RegisterUserDto;
import com.Business_Intelligence.bi_system_backend.entities.User;
import com.Business_Intelligence.bi_system_backend.services.auth.AuthenticationService;
import com.Business_Intelligence.bi_system_backend.services.auth.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        long expirationTime = jwtService.getExpirationTime();
        return ResponseEntity.ok(new LoginResponse(jwtToken, expirationTime));
    }
}
