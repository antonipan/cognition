package ru.antonio.cognition.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.antonio.cognition.models.User;
import ru.antonio.cognition.services.UserServiceImpl;

/**
 *
 *
 * @author Antonio
 * @version 1.1
 */
@Controller
@RequestMapping()
public class RegistrationUserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String welcome () {
        return "forward:/api/api.html";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String addUser (@RequestBody User newUser) {
        userService.addNewUser(newUser);
        String roleName = checkingRole(newUser);
        if(roleName.equalsIgnoreCase("teacher")) {
            return "redirect:helloTeacher";
        }
        return "helloStudent";
    }

    private String checkingRole (User user) {
        return user.getRole().getName();
    }


}
