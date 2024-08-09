package com.example.chat.controller;

import com.example.chat.dto.UserRequest;
import com.example.chat.dto.UserResponse;
import com.example.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    //Да няма try/catch в контролера!!! И да връщаме само статуси
    @GetMapping(value = "/api/v1/users")
    public ResponseEntity<UserResponse> getByUserName(@RequestParam String userName) {
            UserResponse userResponse = userService.getUserResponseByUserName(userName);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody UserRequest userRequest) {
        userService.create(userRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}