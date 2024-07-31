package com.example.chat.controller;

import com.example.chat.enumeration.UserStatus;
import com.example.chat.service.EventHandlerService;
import com.example.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthenticationProvider authenticationProvider;
    private final UserService userService;
    private final EventHandlerService eventHandlerService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session) {
        Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        userService.updateStatus(username, UserStatus.ONLINE);
        eventHandlerService.registerUser(username);
        return "redirect:/chat";
    }

//    @GetMapping("/logout")
    @PostMapping("/api/v1/logout")
    public String logout(Principal principal) {
        SecurityContextHolder.clearContext();
        log.info("User {} logged out", principal.getName());
        userService.updateStatus(principal.getName(), UserStatus.OFFLINE);

        return "redirect:/home";
    }
}