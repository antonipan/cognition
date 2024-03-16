package ru.antonio.cognition.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.models.User;
import ru.antonio.cognition.services.RoleServiceImpl;
import ru.antonio.cognition.services.UserServiceImpl;

/**
 *Класс предназначен для запросов по регистрации и аутентификации пользователей
 *
 * @author Antonio
 * @version 1.1
 */
@Tag(name = "regist")
@Controller
@RequestMapping()
public class RegistrationUserController {

    /**
     * Сервис работы с пользователями
     */
    @Autowired
    private UserServiceImpl userService;

    /**
     * Сервис работы с ролями пользователей
     */
    @Autowired
    private RoleServiceImpl roleService;

    /**
     * Поле необходимо для исполоьзования метоов класса {@link Model}
     */
    private String username;

    /**
     * Показывает пользователям первую страницу приложения.
     * @return
     */
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome () {
        return "forward:/api/welcome.html";
    }

    /**
     * Отображает форму регистрации для {@link User} и классов-наследников {@link ru.antonio.cognition.models.Teacher}
     * {@link ru.antonio.cognition.models.Student}
     *
     * @param model - применяется, чтобы отобразить сущность {@link User}
     * @return - форму
     */
    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String getRegistrationForm (Model model) {
        model.addAttribute("user", new User());
        return "admin/registration";
    }

    /**
     * Post-метод, отправляет данные {@link User} из формы регистрации "admin/registration.html"
     * Метод получает роль из списка ролей базы данных, создаёт пользователя, записывает в него параметры
     * и отправляет пользователя в базу данных.
     *
     * @param name - имя пользователя
     * @param password - пароль пользователя
     * @param roleName - роль пользователя. Должна выбираться из списка (форма не реализована)
     * @return - перебрасывает пользователя на страницу с авторизацией.
     */
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

    /**
     * GET-метод получения страницы авторизации "admin/authentication.html"
     * @param model - отображает пустого пользователя, поля которого заполняются
     * @return - форма авторизации
     */
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String getAuthenticationPage (Model model) {
        model.addAttribute("user", new User());
        return "admin/authentication";
    }

    /**
     * POST-метод отправляет из формы авторизации параметры в сервис пользователя, чтобы проверить его наличие в базе
     * данных.
     * @param name - иммя {@link User} и его наследников
     * @param password - пароль {@link User} и его наследников.
     * @return - при подтверждении вызывает метод {@link #branchOnRoles(String)} для распределения пользователяя
     * по эндпоинтам согласно его роли}. Если проверка не пройдена, метод вернёт форму авторизации.
     */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String getAuthorization (@RequestParam("username") String name,
                                    @RequestParam("password") String password) {
        if(userService.checkingNameAndPassword(name, password)) {
            return branchOnRoles(name);
        }
        return "admin/authentication";
    }

    /**
     * Вспомогательный метод для получения имени пользователя
     * во всех методах класса {@link RegistrationUserController},
     * где используется компонент формы {@link Model}
     * @return
     */
    @ModelAttribute("username")
    private String getUsername () {
        return username;
    }

    /**
     * Вспомогательный метод, используется в методе {@link #getAuthorization(String, String)}
     * данного класса, чтобы отправить пользователя {@link User} на конечную точку согласно его роли {@link Role}.
     * @param name - имя пользователя
     * @return - возвращает форму для {@link ru.antonio.cognition.models.Teacher},
     * если {@link Role} пользователя - учитель; или форму для {@link ru.antonio.cognition.models.Student},
     * если {@link Role} пользователя - студент. Во всех остальных случаях вовзаращает на форму авторизации.
     */
    public String branchOnRoles (String name) {
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
