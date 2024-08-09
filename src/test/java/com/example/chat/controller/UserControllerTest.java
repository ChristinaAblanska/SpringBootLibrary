//package com.example.chat.controller;
//
//import com.example.chat.dto.UserRequest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@AutoConfigureMockMvc
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class UserControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    @WithMockUser("Kate")
//    void givenCorrectUserName_whenGettingUserByUserName_ThenReturnUser() throws Exception {
//        this.mockMvc.perform(get("/api/v1/users")
//                        .param("userName", "johnSmith"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Smith"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("johnSmith"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("john.smith@gmail.com"))
//                .andReturn();
//    }
//
//}