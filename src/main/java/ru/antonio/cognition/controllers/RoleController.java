package ru.antonio.cognition.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.services.RoleService;
import ru.antonio.cognition.services.RoleServiceImplement;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class RoleController {

    public RoleServiceImplement roleService;

    @Autowired
    public RoleController(RoleServiceImplement roleService) {
        this.roleService = roleService;
    }

    @PostMapping()
    public Role saveRole (@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @GetMapping()
    public String getAllRole (Model model) {
        model.addAttribute("roles", roleService.getRoles());
        return "role";
    }
}
