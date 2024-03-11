package ru.antonio.cognition.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.services.RoleServiceImpl;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class RoleController {

    private RoleServiceImpl roleService;

    @Autowired
    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }


    @GetMapping("/role")
    public String getAllRole (Model model) {
        model.addAttribute("roles", roleService.getRoles());
        return "roles";
    }

    @PostMapping("/role")
    @ResponseBody
    public Role save (@RequestBody Role role) {

        return roleService.saveRole(role);
    }


}
