package com.example.chat.controller;

import com.example.chat.dto.ChatDTO;
import com.example.chat.service.EventHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/")
public class SSEController {
    private final EventHandlerService eventHandlerService;

    @PostMapping(value = ("/message"), consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendMessage(Principal principal, @RequestBody ChatDTO chatDTO) {
            boolean sent = eventHandlerService.handleMessage(principal.getName(), chatDTO);
            if (!sent) {
                return new ResponseEntity<>("Message not sent!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Message sent!", HttpStatus.OK);
    }

}