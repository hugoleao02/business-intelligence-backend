package com.Business_Intelligence.bi_system_backend.services.auth;

import com.Business_Intelligence.bi_system_backend.dtos.LoginUserDto;
import com.Business_Intelligence.bi_system_backend.dtos.RegisterUserDto;
import com.Business_Intelligence.bi_system_backend.entities.User;
import com.Business_Intelligence.bi_system_backend.enums.Role;
import com.Business_Intelligence.bi_system_backend.exceptions.UserAlreadyExistsException;
import com.Business_Intelligence.bi_system_backend.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User signup(RegisterUserDto dto){
        Optional<User> existingUser = userRepository.findByEmail(dto.email());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("Email já cadastrado.");
        }

        String encryptedPassword = passwordEncoder.encode(dto.password());

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(encryptedPassword);
        user.setRoles(List.of(Role.values()));
       return userRepository.save(user);
    }

    public User authenticate(LoginUserDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.email(),
                        dto.password()
                )
        );

        return userRepository.findByEmail(dto.email())
                .orElseThrow();
    }
}
