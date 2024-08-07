package com.example.chat.dto;

public record LoginResponse(
        String token,
        long expiresIn
) {
}
