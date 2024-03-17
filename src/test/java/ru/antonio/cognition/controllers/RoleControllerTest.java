package ru.antonio.cognition.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;
import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.services.RoleServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(RoleController.class)
public class RoleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RoleServiceImpl roleService;

    @Autowired
    ObjectMapper objectMapper;

    private WebClient webClient;

    RoleController roleController;

    @BeforeEach
    void setup(WebApplicationContext context) {
        webClient = MockMvcWebClientBuilder
                .webAppContextSetup(context)
                .build();
    }

    @AfterEach
    public void closed () {
        webClient.close();
    }

    @Test
    void getAllRoleTest () throws Exception {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("t"));
        roles.add(new Role ("e"));

        Mockito.when(roleService.getRoles()).thenReturn(roles);

        mvc.perform(get("/admin/role"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/roles"))
                .andExpect(model().attributeExists("roles"))
                .andExpect(model().attribute("roles", roleService.getRoles()));
    }

    @Test
    void saveRoleTest () throws Exception {
        Role role = new Role("t");
        Mockito.when(roleService.saveRole(role)).thenReturn(role);

        mvc.perform(post("/admin/role")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(role)))
                .andExpect(status().isOk());

    }


}
