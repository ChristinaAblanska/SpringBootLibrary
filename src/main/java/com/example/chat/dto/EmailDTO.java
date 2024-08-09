package com.example.chat.dto;

import lombok.*;

import java.io.Serializable;
@AllArgsConstructor
@Getter
public class EmailDTO implements Serializable {

    private String subject;
    private String toList;
    private String body;

}