package ru.antonio.cognition.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RoleTest {

    Role admin;

    @BeforeEach
    public void setup () {
        admin = new Role("admin");
    }

    @Test
    void getAndSetId () {
        admin.setId(33);
        assertEquals(33, admin.getId());
    }

    @Test
    void getAndSetRoleNameTest () {
        assertEquals("admin", admin.getName());
        admin.setName("user");
        assertNotEquals("admin", admin.getName());
    }
}
