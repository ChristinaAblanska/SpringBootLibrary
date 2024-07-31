package com.example.chat.dto;

public record UserResponse(
        String firstName,
        String lastName,
        String userName,
        String email
) {
}