//package com.example.chat.config;
//
//import com.example.chat.enumeration.UserStatus;
//import com.example.chat.model.User;
//import com.example.chat.repository.UserRepository;
//import com.example.chat.service.UserService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class DataInitializer {
//    @Bean
//    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            userRepository.save("Ana", "Stuart", "ana.stuart@gmail.com",
//                    "admin", passwordEncoder.encode("adminpass"), UserStatus.ONLINE.name());
//
//            userRepository.save("Ben", "Davis", "ben.davis@gmail.com",
//                    "user", passwordEncoder.encode("userpass"), UserStatus.ONLINE.name());
//
//        };
//    }
//}
