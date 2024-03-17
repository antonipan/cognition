package ru.antonio.cognition.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;
import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.models.User;
import ru.antonio.cognition.services.RoleServiceImpl;
import ru.antonio.cognition.services.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(RegistrationUserController.class)
public class RegistrationUserControllerTest {

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    public RegistrationUserController regUsController;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private RoleServiceImpl roleService;

    @Autowired
    ObjectMapper objectMapper;

    private WebClient webClient;


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
    void welcomeTest() throws Exception {
        mvc.perform(get("/welcome"))
                .andExpect(status().isOk())
                .andExpect(view().name("forward:/api/welcome.html"));
        String url = regUsController.welcome();

        assertEquals("forward:/api/welcome.html", url);
    }

    @Test
    void getRegistrationFormTest() throws Exception {
        User user = new User();
        mvc.perform(get("/reg")
                        .contentType(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(view().name("admin/registration"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    void createUserTest () throws Exception{
        Role role = new Role("kot");
        Mockito.when(roleService.getRoleByName("kot")).thenReturn(role);
        User user = new User("tom", "123", new Role("kot"));
        Mockito.doNothing().when(userService).addNewUser(user);

        mvc.perform(post("/reg?username=oleg&password=1234&role=teacher"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void getAuthenticationPageTest () throws Exception {
        mvc.perform(get("/auth"))
                .andExpect(status().is(200))
                .andExpect(view().name("admin/authentication"))
                .andExpect(model().attributeExists("user"));
    }
}
