package com.example.chat.dto;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public record UserDTO(
        SseEmitter sseEmitter,
        String userName
) {
}