package com.example.chat.events;

public record ChatEvent(
        String messageContent,
        String userName
) implements CommunicationsEvent {
    @Override
    public String getEventType() {
        return "chat";
    }
}