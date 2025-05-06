package com.github.konarjg.BackendAPI;

import com.github.konarjg.BackendAPI.entity.User;
import com.github.konarjg.BackendAPI.repository.UserRepository;
import com.github.konarjg.BackendAPI.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTests {
    @Test
    public void findByEmail_whenEmailIsNull_shouldReturnNull() {
        //Arrange
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);
        String email = null;

        //Act
        User user = userService.findByEmail(email);

        //Assert
        assertNull(user);
    }

    @Test
    public void findByEmail_whenEmailIsEmpty_shouldReturnNull() {
        //Arrange
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);
        String email = "";

        //Act
        User user = userService.findByEmail(email);

        //Assert
        assertNull(user);
    }

    @Test
    public void findByEmail_whenNoValidUserInDatabase_shouldReturnNull() {
        //Arrange
        String email = "test@test.com";

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(email)).thenReturn(null);

        UserService userService = new UserService(userRepository);

        //Act
        User result = userService.findByEmail(email);

        //Assert
        assertNull(result);
    }

    @Test
    public void findByEmail_whenValidUserInDatabase_shouldReturnValidUser() {
        //Arrange
        String email = "test@test.com";
        User user = new User();
        user.setEmail(email);

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(email)).thenReturn(user);

        UserService userService = new UserService(userRepository);

        //Act
        User result = userService.findByEmail(email);

        //Assert
        assertEquals(email, result.getEmail());
    }

    @Test
    public void existsByEmail_whenEmailIsNull_shouldReturnFalse() {
        //Arrange
        String email = null;
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        //Act
        boolean result = userService.existsByEmail(email);

        //Assert
        assertFalse(result);
    }

    @Test
    public void existsByEmail_whenEmailIsEmpty_shouldReturnFalse() {
        //Arrange
        String email = "";
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        //Act
        boolean result = userService.existsByEmail(email);

        //Assert
        assertFalse(result);
    }

    @Test
    public void existsByEmail_whenNoValidUserInDatabase_shouldReturnFalse() {
        //Arrange
        String email = "test@test.com";
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.existsByEmail(email)).thenReturn(false);

        UserService userService = new UserService(userRepository);

        //Act
        boolean result = userService.existsByEmail(email);

        //Assert
        assertFalse(result);
    }

    @Test
    public void existsByEmail_whenValidUserInDatabase_shouldReturnTrue() {
        //Arrange
        String email = "test@test.com";
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.existsByEmail(email)).thenReturn(true);

        UserService userService = new UserService(userRepository);

        //Act
        boolean result = userService.existsByEmail(email);

        //Assert
        assertTrue(result);
    }

    @Test
    public void findByEmailAndPassword_whenEmailIsNull_shouldReturnNull() {
        //Arrange
        String email = null;
        String password = "test";
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        //Act
        User user = userService.findByEmailAndPassword(email, password);

        //Assert
        assertNull(user);
    }

    @Test
    public void findByEmailAndPassword_whenEmailIsEmpty_shouldReturnNull() {
        //Arrange
        String email = "";
        String password = "test";
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        //Act
        User user = userService.findByEmailAndPassword(email, password);

        //Assert
        assertNull(user);
    }

    @Test
    public void findByEmailAndPassword_whenPasswordIsNull_shouldReturnNull() {
        //Arrange
        String email = "test@test.com";
        String password = null;
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        //Act
        User user = userService.findByEmailAndPassword(email, password);

        //Assert
        assertNull(user);
    }

    @Test
    public void findByEmailAndPassword_whenPasswordIsEmpty_shouldReturnNull() {
        //Arrange
        String email = "test@test.com";
        String password = "";
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        //Act
        User user = userService.findByEmailAndPassword(email, password);

        //Assert
        assertNull(user);
    }

    @Test
    public void findByEmailAndPassword_whenBothCredentialsAreNull_shouldReturnNull() {
        //Arrange
        String email = null;
        String password = null;
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        //Act
        User user = userService.findByEmailAndPassword(email, password);

        //Assert
        assertNull(user);
    }

    @Test
    public void findByEmailAndPassword_whenBothCredentialsAreEmpty_shouldReturnNull() {
        //Arrange
        String email = "";
        String password = "";
        UserRepository userRepository = mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        //Act
        User user = userService.findByEmailAndPassword(email, password);

        //Assert
        assertNull(user);
    }

    @Test
    public void findByEmailAndPassword_whenEmailIsWrong_shouldReturnNull() {
        //Arrange
        String email = "test@test.com";
        String password = "test";

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmailAndPassword(email, password)).thenReturn(null);

        UserService userService = new UserService(userRepository);

        //Act
        User result = userService.findByEmailAndPassword(email, password);

        //Assert
        assertNull(result);
    }

    @Test
    public void findByEmailAndPassword_whenPasswordIsWrong_shouldReturnNull() {
        //Arrange
        String email = "test@test.com";
        String password = "test";

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmailAndPassword(email, password)).thenReturn(null);

        UserService userService = new UserService(userRepository);

        //Act
        User result = userService.findByEmailAndPassword(email, password);

        //Assert
        assertNull(result);
    }

    @Test
    public void findByEmailAndPassword_whenBothCredentialsWrong_shouldReturnNull() {
        //Arrange
        String email = "test@test.com";
        String password = "test";

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmailAndPassword(email, password)).thenReturn(null);

        UserService userService = new UserService(userRepository);

        //Act
        User result = userService.findByEmailAndPassword(email, password);

        //Assert
        assertNull(result);
    }

    @Test
    public void findByEmailAndPassword_whenBothCredentialsCorrect_shouldReturnValidUser() {
        //Arrange
        String email = "test@test.com";
        String password = "test";
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmailAndPassword(email, password)).thenReturn(user);

        UserService userService = new UserService(userRepository);

        //Act
        User result = userService.findByEmailAndPassword(email, password);

        //Assert
        assertEquals(user.getEmail(), result.getEmail());
    }
}
