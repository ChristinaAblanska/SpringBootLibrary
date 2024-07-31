package com.example.chat.model;

import com.example.chat.enumeration.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private UserStatus status;

    public boolean isEmpty() {
        return userName.isEmpty() || email.isEmpty() || password.isEmpty();
    }
}