package ru.antonio.cognition.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.services.RoleServiceImpl;

/**
 * Класс контроллера управления ролями.
 *
 * @author Antonio
 * @version 1.0
 */
@Controller
@RequestMapping("/admin")
public class RoleController {
    /**
     * Сервис управления ролями
     */
    private RoleServiceImpl roleService;

    /**
     * @param roleService - сервис управления ролями
     */
    @Autowired
    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    /**
     * GET-метод получения списка ролей.
     * @param model - отображает список ролей
     * @return страница отображения ролей "admin/roles"
     */
    @GetMapping("/role")
    public String getAllRole (Model model) {
        model.addAttribute("roles", roleService.getRoles());
        return "admin/roles";
    }

    /**
     * POST-метод. Сохраняет роль
     * @param role - роль, которая требуется для сохранения.
     * @return - возвращает роль.
     */
    @PostMapping("/role")
    @ResponseBody
    public Role save (@RequestBody Role role) {
        return roleService.saveRole(role);
    }


}
