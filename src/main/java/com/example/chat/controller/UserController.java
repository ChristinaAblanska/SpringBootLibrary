//package com.example.chat.controller;
//
//import com.example.chat.dto.UserResponse;
//import com.example.chat.errorHandling.BusinessNotFound;
//import com.example.chat.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequiredArgsConstructor
//@Validated
//public class UserController {
//    private final UserService userService;
//
//    @GetMapping(value = "/api/v1/users")
//    public ResponseEntity<UserResponse> getByUserName(@RequestParam String userName) {
//        try {
//            UserResponse userResponse = userService.getUserResponseByUserName(userName);
//            return new ResponseEntity<>(userResponse, HttpStatus.OK);
//        } catch (BusinessNotFound e) {
//            // Как да хвана правилните статус и съобщение?
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//}