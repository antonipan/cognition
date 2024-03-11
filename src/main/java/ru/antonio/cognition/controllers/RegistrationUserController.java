package ru.antonio.cognition.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.models.User;
import ru.antonio.cognition.services.RoleService;
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

    @Autowired
    private Model model;

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String welcome () {
        return "forward:/api/api.html";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String getRegistrationForm () {
        model.addAttribute("user", new User());
        return "user/registration";
    }


    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String createUser (@RequestParam("username") String name,
                              @RequestParam("password") String password,
                              @RequestParam("role") String roleName) {

        Role role1 = roleService.getRoleByName(roleName);
        User newUser = new User(name, password, role1);
        userService.addNewUser(newUser);
//        if(roleName.equalsIgnoreCase("teacher")) {
//            User user = userService.getUserByName(newUser.getUsername());
//            return "redirect:/teachers/" + user.getId();
//        } else if (roleName.equalsIgnoreCase("student")) {
//            User user = userService.getUserByName(newUser.getUsername());
//            return "redirect:/auth";
//        }
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
    public String getAuthenticationPage () {
//        model.addAttribute("user", )
        Object user = model.getAttribute("user");
        User user1 = (User) user;
        assert user1 != null;
        model.addAttribute("user", userService.getUserByName(user1.getUsername()));
        return "user/authentication";
    }
}
