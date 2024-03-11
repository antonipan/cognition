package ru.antonio.cognition.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.models.User;
import ru.antonio.cognition.services.RoleServiceImpl;
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

    @Autowired
    private RoleServiceImpl roleService;

    private String username;

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String welcome () {
        return "forward:/api/api.html";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String getRegistrationForm (Model model) {
        model.addAttribute("user", new User());
        return "user/registration";
    }


    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String createUser (@RequestParam("username") String name,
                              @RequestParam("password") String password,
                              @RequestParam("role") String roleName) {
        username = name;
        Role role1 = roleService.getRoleByName(roleName);
        User newUser = new User(name, password, role1);
        userService.addNewUser(newUser);
        return "redirect:/auth";
    }

    /**
     * Вспомогательный метод получения роли пользователя. Нужен, чтобы по роли пользователя
     * перекидывать его на нужную страницу.
     * @param user - пользователь {@link User}
     * @return - имя роли {@link ru.antonio.cognition.models.Role}
     */
    private String getRoleUser(User user) {
        return user.getRole().getName();
    }


    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String getAuthenticationPage (Model model) {
        model.addAttribute("user", new User());
        return "user/authentication";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String getAuthorization (@RequestParam("username") String name, @RequestParam("password") String password) {
        if(userService.checkingNameAndPassword(name, password)) {
            return branchOnRoles(name);
        }
        return "user/authentication";
    }

    @ModelAttribute("username")
    public String getUsername () {
        return username;
    }

    private String branchOnRoles (String name) {
        User user = userService.getUserByName(name);
        if(getRoleUser(user).equalsIgnoreCase("teacher")) {
            return "redirect:/teachers/" + user.getId();
        } else if (getRoleUser(user).equalsIgnoreCase("student")) {
            return "redirect:/students/" + user.getId();
        } else {
            return "user/authentication";
        }
    }
}
