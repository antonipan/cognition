package ru.antonio.cognition.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserTest {

    private User user;
    private Role role;

    @BeforeEach
    void setup () {
        role = new Role("user");
        user = new User("ivan", "1234", role);
    }

    @Test
    void getIdTest () {
        assertNull(user.getId());
    }

    @Test
    void setIdTest () {
        user.setId(333L);
        assertEquals(333, user.getId());
    }

    @Test
    void getUsernameTest () {
        assertEquals("ivan", user.getUsername());
    }

    @Test
    void setUsernameTest () {
        user.setUsername("igor");
        assertEquals("igor", user.getUsername());
    }

    @Test
    void getPasswordTest () {
        assertEquals("1234", user.getPassword());
    }

    @Test
    void setPasswordTest () {
        user.setPassword("321");
        assertEquals("321", user.getPassword());
    }

    @Test
    void getRoleTest () {
        assertNotNull(user.getRole());
    }

    @Test
    void setRoleTest () {
        user.setRole(null);
        assertNull(user.getRole());
    }
}
