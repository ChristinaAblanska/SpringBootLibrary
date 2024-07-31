package com.example.chat.service;

import com.example.chat.dto.UserDTO;
import com.example.chat.enumeration.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class EventHandlerServiceTest {
    UserService userService;
    MessageService messageService;
    EventHandlerService eventHandlerService;
    AtomicInteger ID_Counter = new AtomicInteger(1);
    long DEFAULT_TIMEOUT = Long.MAX_VALUE;
    Set<UserDTO> loggedInUsers = new HashSet<>();

    @BeforeEach
    void setUp() {
        userService = Mockito.mock(UserService.class);
        messageService = Mockito.mock(MessageService.class);
        eventHandlerService = new EventHandlerService(userService, messageService);
    }

    @Test
    void registerUser() {

    }

    @Test
    void sendAllPending() {
    }

    @Test
    void sendMsg() {
    }

    @Test
    void storeMsgForLater() {
    }
}