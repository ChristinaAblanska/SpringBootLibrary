package com.example.chat.service;

import com.example.chat.dto.EmailDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendSimpleEmail(EmailDTO emailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDTO.getToList());
        message.setSubject(emailDTO.getSubject());
        message.setText(emailDTO.getBody());

        log.info("Request to EmailService: send email with subject" + emailDTO.getSubject()
                + " to email address: " + emailDTO.getToList());
        mailSender.send(message);
    }
}