package com.example.chat.controller;

import com.example.chat.dto.ChatDTO;
import com.example.chat.enumeration.UserStatus;
import com.example.chat.model.User;
import com.example.chat.service.EventHandlerService;
import com.example.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/")
public class SSEController {
    private final EventHandlerService eventHandlerService;
    private final UserService userService;

    @PostMapping(value = ("/message"), consumes = MediaType.APPLICATION_JSON_VALUE)
    public String sendMessage(Principal principal, @RequestBody ChatDTO chatDTO) {
        User user = userService.getUserByUserName(chatDTO.userName());
        boolean sent = false;
        if (user.getStatus().equals(UserStatus.ONLINE)) {
            sent = eventHandlerService.sendMsg(chatDTO, principal.getName());
        }
        if (sent) {
            return "Message sent!";
        } else {
            log.info("Message not sent to userName: {}", chatDTO.userName());
            eventHandlerService.storeMsgForLater(chatDTO, principal.getName());
            return "Unable to sent message";
        }
    }

    @PostMapping("/register-user")
    public ResponseEntity<SseEmitter> sseEmitter(Principal principal) {
        if (userService.getStatus(principal.getName()).equals(UserStatus.ONLINE)) {
//            eventHandlerService.registerUser(userName)
            return new ResponseEntity<>(eventHandlerService.registerUser(principal.getName()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}