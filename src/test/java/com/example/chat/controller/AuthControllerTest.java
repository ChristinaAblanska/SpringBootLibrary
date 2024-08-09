package com.example.chat.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenCorrectCredentials_whenLoginIn_ThenRedirectToChat() throws Exception {
                this.mockMvc.perform(post("/login")
                        .param("username", "Kate")
                        .param("password", "password")).andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser("Kate")
    void givenCorrectCredentials_whenLoginOut_ThenRedirectToHome() throws Exception {
        this.mockMvc.perform(post("/api/v1/logout")).andDo(print())
                .andExpect(status().isOk());
    }
}