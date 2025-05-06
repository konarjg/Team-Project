package com.github.konarjg.BackendAPI;

import com.github.konarjg.BackendAPI.entity.User;
import com.github.konarjg.BackendAPI.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    TestEntityManager entityManager;

    @Test
    public void findByEmail_whenEmailIsNull_shouldReturnNull() {
        //Arrange
        String email = null;

        //Act
        User user = userRepository.findByEmail(email);

        //Assert
        assertNull(user);
    }

    @Test
    public void findByEmail_whenEmailIsEmpty_shouldReturnNull() {
        //Arrange
        String email = "";

        //Act
        User user = userRepository.findByEmail(email);

        //Assert
        assertNull(user);
    }

    @Test
    public void findByEmail_whenNoValidUserInDatabase_shouldReturnNull() {
        //Arrange
        String email = "test@test.com";
        User user = new User();
        user.setEmail("other@test.com");
        user.setPassword("test");

        entityManager.persist(user);

        //Act
        User result = userRepository.findByEmail(email);

        //Assert
        assertNull(result);
    }

    @Test
    public void findByEmail_whenValidUserInDatabase_shouldReturnValidUser() {
        //Arrange
        String email = "test@test.com";
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");

        entityManager.persist(user);

        //Act
        User result = userRepository.findByEmail(email);

        //Assert
        assertEquals(email, result.getEmail());
    }

    @Test
    public void existsByEmail_whenEmailIsNull_shouldReturnFalse() {
        //Arrange
        String email = null;

        //Act
        boolean result = userRepository.existsByEmail(email);

        //Assert
        assertFalse(result);
    }

    @Test
    public void existsByEmail_whenEmailIsEmpty_shouldReturnFalse() {
        //Arrange
        String email = "";

        //Act
        boolean result = userRepository.existsByEmail(email);

        //Assert
        assertFalse(result);
    }

    @Test
    public void existsByEmail_whenNoValidUserInDatabase_shouldReturnFalse() {
        //Arrange
        String email = "test@test.com";
        User user = new User();
        user.setEmail("other@test.com");
        user.setPassword("test");

        entityManager.persist(user);

        //Act
        boolean result = userRepository.existsByEmail(email);

        //Assert
        assertFalse(result);
    }

    @Test
    public void existsByEmail_whenValidUserInDatabase_shouldReturnTrue() {
        //Arrange
        String email = "test@test.com";
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");

        entityManager.persist(user);

        //Act
        boolean result = userRepository.existsByEmail(email);

        //Assert
        assertTrue(result);
    }

    @Test
    public void findByEmailAndPassword_whenEmailIsNull_shouldReturnNull() {
        //Arrange
        String email = null;
        String password = "test";

        //Act
        User user = userRepository.findByEmailAndPassword(email, password);

        //Assert
        assertNull(user);
    }

    @Test
    public void findByEmailAndPassword_whenEmailIsEmpty_shouldReturnNull() {
        //Arrange
        String email = "";
        String password = "test";

        //Act
        User user = userRepository.findByEmailAndPassword(email, password);

        //Assert
        assertNull(user);
    }

    @Test
    public void findByEmailAndPassword_whenPasswordIsNull_shouldReturnNull() {
        //Arrange
        String email = "test@test.com";
        String password = null;

        //Act
        User user = userRepository.findByEmailAndPassword(email, password);

        //Assert
        assertNull(user);
    }

    @Test
    public void findByEmailAndPassword_whenPasswordIsEmpty_shouldReturnNull() {
        //Arrange
        String email = "test@test.com";
        String password = "";

        //Act
        User user = userRepository.findByEmailAndPassword(email, password);

        //Assert
        assertNull(user);
    }

    @Test
    public void findByEmailAndPassword_whenBothCredentialsAreNull_shouldReturnNull() {
        //Arrange
        String email = null;
        String password = null;

        //Act
        User user = userRepository.findByEmailAndPassword(email, password);

        //Assert
        assertNull(user);
    }

    @Test
    public void findByEmailAndPassword_whenBothCredentialsAreEmpty_shouldReturnNull() {
        //Arrange
        String email = "";
        String password = "";

        //Act
        User user = userRepository.findByEmailAndPassword(email, password);

        //Assert
        assertNull(user);
    }

    @Test
    public void findByEmailAndPassword_whenEmailIsWrong_shouldReturnNull() {
        //Arrange
        String email = "test@test.com";
        String password = "test";

        User user = new User();
        user.setEmail("other@test.com");
        user.setPassword("test");

        entityManager.persist(user);

        //Act
        User result = userRepository.findByEmailAndPassword(email, password);

        //Assert
        assertNull(result);
    }

    @Test
    public void findByEmailAndPassword_whenPasswordIsWrong_shouldReturnNull() {
        //Arrange
        String email = "test@test.com";
        String password = "test";

        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("other");

        entityManager.persist(user);

        //Act
        User result = userRepository.findByEmailAndPassword(email, password);

        //Assert
        assertNull(result);
    }

    @Test
    public void findByEmailAndPassword_whenBothCredentialsWrong_shouldReturnNull() {
        //Arrange
        String email = "test@test.com";
        String password = "test";

        User user = new User();
        user.setEmail("other@test.com");
        user.setPassword("other");

        entityManager.persist(user);

        //Act
        User result = userRepository.findByEmailAndPassword(email, password);

        //Assert
        assertNull(result);
    }

    @Test
    public void findByEmailAndPassword_whenBothCredentialsCorrect_shouldReturnValidUser() {
        //Arrange
        String email = "test@test.com";
        String password = "test";

        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("test");

        userRepository.save(user);

        //Act
        User result = userRepository.findByEmailAndPassword(email, password);

        //Assert
        assertEquals(user.getEmail(), result.getEmail());
    }
}
