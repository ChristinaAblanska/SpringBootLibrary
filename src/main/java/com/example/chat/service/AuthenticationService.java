package com.example.chat.service;

import com.example.chat.dto.UserRequest;
import com.example.chat.dto.LoginUserDTO;
import com.example.chat.enumeration.UserStatus;
import com.example.chat.model.User;
import com.example.chat.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(UserRequest userRequest) {
        log.info("Request to DB: create new user with userName: {}", userRequest.userName());
        userRepository.save(userRequest.firstName(), userRequest.lastName(), userRequest.email(), userRequest.userName(),
                passwordEncoder.encode(userRequest.password()), UserStatus.ONLINE.name());
        return userRepository.getByUserName(userRequest.userName());
    }

    public User authenticate(LoginUserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.userName(),
                        input.password()
                )
        );

        return userRepository.findByUserName(input.userName())
                .orElseThrow();
    }
}
