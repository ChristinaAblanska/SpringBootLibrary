package com.example.chat.events;

public record WelcomeEvent(String userName) implements CommunicationsEvent {

    @Override
    public String getEventType() {
        return "welcome";
    }
}