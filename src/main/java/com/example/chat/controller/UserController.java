package com.example.chat.controller;

import com.example.chat.dto.UserRequest;
import com.example.chat.dto.UserResponse;
import com.example.chat.errorHandling.BusinessNotFound;
import com.example.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> getByUserName(@RequestParam String userName) {
        try {
            UserResponse userResponse = userService.getDTOByUserName(userName);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } catch (BusinessNotFound e) {
            return new ResponseEntity<>(new UserResponse("", "", "", ""),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody UserRequest userRequest) {
        userService.create(userRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}