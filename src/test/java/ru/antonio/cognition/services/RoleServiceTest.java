package ru.antonio.cognition.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.models.User;
import ru.antonio.cognition.repositories.RoleDao;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RoleServiceTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleDao roleDao;

    private Role newRole;
    private List <Role> roleList;
    private User user;

    @BeforeEach
    public void setup () {
        newRole = new Role("logic");
        roleList = new ArrayList<>();
        roleList.add(newRole);
        user = new User("anton", "123");
    }

    @Test
    void saveRoleTest () {

        Role findRole = roleDao.findByName("logic").orElse(null);
        if(findRole == null) {
            when(roleDao.save(newRole)).thenReturn(newRole);
        }

        assertEquals(newRole, roleService.saveRole(newRole));

        Role oldRole = new Role("bio");
        when(roleDao.findByName("bio")).thenReturn(Optional.of(oldRole));

        Role findByName1 = roleDao.findByName("bio").orElse(null);
        assert findByName1 != null;
        assertThrows(RuntimeException.class, () -> roleService.saveRole(findByName1));

    }

    @Test
    void getRolesTest () {
        when(roleDao.findAll()).thenReturn(roleList);
        assertEquals(1, roleService.getRoles().size());
    }

    @Test
    void assignRoleToUserTest () {
        when(roleDao.findByName("logic")).thenReturn(Optional.of(newRole));

        assertNull(user.getRole());
        user = roleService.assignRoleToUser(user, "logic");
        assertEquals("logic", user.getRole().getName());

        verify(roleDao, times(1)).findByName("logic");
    }

    @Test
    void getRoleByNameTest () {
        Role oldRole = new Role("bio");
        when(roleDao.findByName("bio")).thenReturn(Optional.of(oldRole));

        assertEquals(Role.class, roleService.getRoleByName("bio").getClass());
        assertNotEquals("logic", roleService.getRoleByName("bio").getName());
    }

}
