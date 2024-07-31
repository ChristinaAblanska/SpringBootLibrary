package com.example.chat.model;

import com.example.chat.enumeration.MessageStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Message {
    private long id;
    private String content;
    private User sender;
    private User receiver;
    private MessageStatus status;
    private LocalDateTime timestamp;
}