package ru.antonio.cognition.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.antonio.cognition.models.User;
import ru.antonio.cognition.services.RegistrationServiceImpl;

/**
 *
 *
 * @author Antonio
 * @version 1.1
 */
@RestController
@RequestMapping
public class RegistrationUserController {

    @Autowired
    private RegistrationServiceImpl registrationService;

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String welcome () {
        return "forward:/api/api.html";
    }

    @RequestMapping(value = "/new-user", method = RequestMethod.POST)
    public String addUser (@RequestBody User newUser) {
        registrationService.addNewUser(newUser);
        return "user saved";
    }
}
