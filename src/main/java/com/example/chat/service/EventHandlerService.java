package com.example.chat.service;

import com.example.chat.dto.ChatDTO;
import com.example.chat.dto.EmailDTO;
import com.example.chat.dto.UserDTO;
import com.example.chat.enumeration.MessageStatus;
import com.example.chat.enumeration.UserStatus;
import com.example.chat.errorHandling.BusinessNotFound;
import com.example.chat.events.ChatEvent;
import com.example.chat.events.CommunicationsEvent;
import com.example.chat.events.WelcomeEvent;
import com.example.chat.model.Message;
import com.example.chat.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventHandlerService {
    private static final AtomicInteger ID_Counter = new AtomicInteger(1);
    public static final long DEFAULT_TIMEOUT = Long.MAX_VALUE;
    private final Set<UserDTO> loggedInUsers = new HashSet<>();

    private final UserService userService;
    private final MessageService messageService;
    private final EmailService emailService;

    public SseEmitter registerUser(String userName) {
        SseEmitter sseEmitter = new SseEmitter(DEFAULT_TIMEOUT);
        UserDTO userDTO = new UserDTO(sseEmitter, userName);

        sseEmitter.onCompletion(() -> loggedInUsers.remove(userDTO));
        sseEmitter.onError((err) -> removeAndLogError(userDTO));
        sseEmitter.onTimeout(() -> removeAndLogError(userDTO));

        loggedInUsers.add(userDTO);
        userService.updateStatus(userName, UserStatus.ONLINE);
        sendWelcomeToClient(userDTO);

        List<Message> pendingMessages = messageService.getAllPendingTo(userDTO.userName());
        if (!pendingMessages.isEmpty()) {
            sendAllPending(pendingMessages, userDTO);
        }

        log.info("New user logged in: {}", userDTO.userName());
        return sseEmitter;
    }

    private void removeAndLogError(UserDTO userDTO) {
        log.info("Error during communication. The user {} is not logged in!", userDTO.userName());
        loggedInUsers.remove(userDTO);
    }

    private void sendWelcomeToClient(UserDTO userDTO) {
        WelcomeEvent welcomeEvent = new WelcomeEvent(userDTO.userName());
        sendMessage(userDTO, welcomeEvent);
    }


//    public void broadcast(ChatDTO chatDTO) {
//        Set<UserDTO> users = Set.copyOf(loggedInUsers);
//        for (UserDTO user : users) {
//            ChatEvent chatEvent = new ChatEvent(chatDTO.messageContent(), chatDTO.userName());
//            sendMessage(user, chatEvent);
//        }
//    }

    public void sendAllPending(List<Message> pendingMessages, UserDTO userDTO) {
        for (Message message : pendingMessages) {
            ChatEvent chatEvent = new ChatEvent(message.getContent(), userDTO.userName());
            sendMessage(userDTO, chatEvent);
            messageService.updateMessage(message.getId(), MessageStatus.SENT, LocalDateTime.now());
        }
    }

    public boolean sendMsg(ChatDTO chatDTO, String sender) {
        boolean sent = false;
        if (!userService.existsByUserName(chatDTO.userName())) {
            throw new BusinessNotFound("Not a registered user!");
        }
        Set<UserDTO> users = Set.copyOf(loggedInUsers);
        for (UserDTO user : users) {
            if (user.userName().equals(chatDTO.userName())) {
                ChatEvent chatEvent = new ChatEvent(chatDTO.messageContent(), chatDTO.userName());
                sendMessage(user, chatEvent);
                messageService.create(chatDTO, sender, MessageStatus.SENT, LocalDateTime.now());
                sent = true;
                break;
            }
        }
        return sent;
    }

    public void storeMsgForLater(ChatDTO chatDTO, String sender) {
        messageService.create(chatDTO, sender, MessageStatus.PENDING, LocalDateTime.now());
        User user = userService.getUserByUserName(chatDTO.userName());
        String body = "Dear Mr/Ms " + user.getLastName() + ",\n\n"
                + "Please note that you have a new message from "
                + sender
                + ", waiting to be opened!\n"
                + "Please log in to do so!\n\n"
                + "Regards,\nCake Shop Chat";
        EmailDTO emailDTO = new EmailDTO("You have new pending message", user.getEmail(), body);
        emailService.sendSimpleEmail(emailDTO);

        log.info("Message stored as PENDING for userName: {}", chatDTO.userName());
    }

    private void sendMessage(UserDTO userDTO, CommunicationsEvent communicationsEvent) {
        SseEmitter sseEmitter = userDTO.sseEmitter();
        try {
            log.info("Notify user {}", userDTO.userName());
            int eventID = ID_Counter.incrementAndGet();
            SseEmitter.SseEventBuilder eventBuilder = event().name(communicationsEvent.getEventType())
                    .id(String.valueOf(eventID))
                    .data(communicationsEvent, MediaType.APPLICATION_JSON);
            sseEmitter.send(eventBuilder);
        } catch (IOException e) {
            sseEmitter.completeWithError(e);
        }
    }

    public boolean handleMessage(String userName, ChatDTO chatDTO) {
        User user = userService.getUserByUserName(chatDTO.userName());
        boolean sent = false;
        if (user.getStatus().equals(UserStatus.ONLINE)) {
            sent = sendMsg(chatDTO, userName);
        }
        if (!sent) {
            storeMsgForLater(chatDTO,userName);
        }
        return sent;
    }
}