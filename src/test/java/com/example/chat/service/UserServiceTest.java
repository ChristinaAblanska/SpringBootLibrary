package com.example.chat.service;

import com.example.chat.dto.UserRequest;
import com.example.chat.dto.UserResponse;
import com.example.chat.enumeration.UserStatus;
import com.example.chat.errorHandling.BusinessNotFound;
import com.example.chat.model.User;
import com.example.chat.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserRepository userRepository;
    UserService userService;
    User user;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
        user = new User(12L, "Dan", "Brown", "dan.brown@gmail.com",
                "danBrown",
                "$2a$10$W5fwnQAG.yqpBr4WJ7neFeUDmVxe4DOcCqIDd0O1QEPIuYCbImKIu", UserStatus.ONLINE);
    }

    @Test
    void givenValidId_whenGettingUserById_thenReturnUser() {
        long id = user.getId();
        User expectedUser = user;
        Mockito.when(userRepository.getById(id)).thenReturn(expectedUser);
        User actualUser = userService.getById(id);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void givenInValidId_whenGettingUserById_thenExceptionThrown() {
        long id = user.getId();
        Mockito.when(userRepository.getById(id)).thenReturn(null);
        BusinessNotFound exception = assertThrows(BusinessNotFound.class, () -> userService.getById(id));
        String expectedMessage = "User with id: " + id + " not found!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenValidUserName_whenGettingUserByUserName_thenReturnUser() {
        String userName = user.getUserName();
        User expectedUser = user;
        Mockito.when(userRepository.getByUserName(userName)).thenReturn(expectedUser);
        User actualUser = userService.getUserByUserName(userName);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void givenInValidUserName_whenGettingUserByUserName_thenExceptionThrown() {
        String userName = user.getUserName();
        Mockito.when(userRepository.getByUserName(userName)).thenReturn(null);
        BusinessNotFound exception = assertThrows(BusinessNotFound.class,
                () -> userService.getUserByUserName(userName));
        String expectedMessage = "User with userName: " + userName + " not found!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenValidUserName_whenGettingDTOByUserName_thenReturnUserResponse() {
        String userName = user.getUserName();
        UserResponse expectedUser = new UserResponse(user.getFirstName(), user.getLastName(),
                user.getUserName(), user.getEmail());
        Mockito.when(userRepository.getByUserName(userName)).thenReturn(user);
        UserResponse actualUser = userService.getDTOByUserName(userName);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void givenInValidUserName_whenGettingDTOByUserName_thenExceptionThrown() {
        String userName = user.getUserName();
        Mockito.when(userRepository.getByUserName(userName)).thenReturn(null);
        BusinessNotFound exception = assertThrows(BusinessNotFound.class,
                () -> userService.getDTOByUserName(userName));
        String expectedMessage = "User with userName: " + userName + " not found!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenValidUserName_whenGettingStatusByUserName_thenReturnStatus() {
        String userName = user.getUserName();
        UserStatus expectedStatus = user.getStatus();
        Mockito.when(userRepository.getStatusByUserName(userName)).thenReturn(expectedStatus);
        UserStatus actualStatus = userService.getStatus(userName);
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void create() {
        UserRequest userRequest = new UserRequest(user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getUserName(), user.getPassword());
        userService.create(userRequest);
        Mockito.verify(userRepository, Mockito.times(1)).save(userRequest.firstName(), userRequest.lastName(), userRequest.email(), userRequest.userName(),
                user.getPassword(), UserStatus.ONLINE.name());
    }

    @Test
    void updateStatusTest() {
        String userName = user.getUserName();
        userService.updateStatus(userName, UserStatus.OFFLINE);
        Mockito.verify(userRepository, Mockito.times(1))
                .updateStatusByUserName(userName, UserStatus.OFFLINE.name());
    }

    @Test
    void givenValidId_whenDeletingUserById_thenDeleteUser() {
        long id = user.getId();
        User expectedUser = user;
        Mockito.when(userRepository.getById(id)).thenReturn(expectedUser);
        userService.delete(id);
        Mockito.verify(userRepository, Mockito.times(1)).delete(id);
    }

    @Test
    void givenInValidId_whenDeletingUserById_thenExceptionThrown() {
        long id = user.getId();
        Mockito.when(userRepository.getById(id)).thenReturn(null);
        BusinessNotFound exception = assertThrows(BusinessNotFound.class, () -> userService.getById(id));
        String expectedMessage = "User with id: " + id + " not found!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenValidId_whenCheckingIfUserExistsById_thenReturnTRUE() {
        long id = user.getId();
        User expectedUser = user;
        Mockito.when(userRepository.getById(id)).thenReturn(expectedUser);
        assertTrue(userService.existsById(id));
    }

    @Test
    void givenInValidId_whenCheckingIfUserExistsById_thenReturnFALSE() {
        long id = user.getId();
        User expectedUser = null;
        Mockito.when(userRepository.getById(id)).thenReturn(expectedUser);
        assertFalse(userService.existsById(id));
    }

    @Test
    void givenValidUserName_whenCheckingIfUserExistsByUserName_thenReturnTRUE() {
        String userName = user.getUserName();
        User expectedUser = user;
        Mockito.when(userRepository.getByUserName(userName)).thenReturn(expectedUser);
        assertTrue(userService.existsByUserName(userName));
    }

    @Test
    void givenInValidUserName_whenCheckingIfUserExistsByUserName_thenReturnFALSE() {
        String userName = user.getUserName();
        User expectedUser = null;
        Mockito.when(userRepository.getByUserName(userName)).thenReturn(expectedUser);
        assertFalse(userService.existsByUserName(userName));
    }
}