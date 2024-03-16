package ru.antonio.cognition.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antonio.cognition.models.Student;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.models.User;
import ru.antonio.cognition.repositories.UserDao;

import java.util.List;

/**
 * Класс упралвения бизнес-логикой сущности {@link User}
 *
 * @author Antonio
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDao<User> userDao;
    private RoleServiceImpl roleService;
    private TeacherServiceImpl teacherService;
    private StudentServiceImpl studentService;

    @Autowired
    public UserServiceImpl(UserDao<User> userDao,
                           RoleServiceImpl roleService,
                           TeacherServiceImpl teacherService,
                            StudentServiceImpl studentService) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @Override
    public void addNewUser(User newUser) {
        // newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setPassword(newUser.getPassword());

        // Проверяем, существует ли пользователь с таким именем.
        if(getUserByName(newUser.getUsername()) != null) {
            throw new RuntimeException("Such user ready exists");
        }
        // Проверяем, роль юзера есть ли в базе данных.
        if(!roleService.checkExistOfRole(newUser.getRole())) {
            throw new RuntimeException("There is no such role in the database.");
        }
//        // Назначаем пользователю роль из списка ролей базы данных.
//        roleService.assignRoleToUser(newUser);
        // Определяем роль пользователя.
        if(newUser.getRole().getName().equalsIgnoreCase("teacher")) {
            // TODO: 09.03.2024 посмотреть преобразование типов в спринг.
            Teacher teacher = new Teacher(newUser.getUsername(),
                    newUser.getPassword(), newUser.getRole());
            teacherService.saveTeacher(teacher);
        } else if (newUser.getRole().getName().equalsIgnoreCase("student")) {
            Student student = new Student(newUser.getUsername(),
                    newUser.getPassword(), newUser.getRole());
            studentService.saveStudent(student);
        }
    }

    @Override
    public List<User> getAllUser() {
        return userDao.findAll();
    }

    @Override
    public User getUserByName(String username) {
        return userDao.findByUsername(username).orElse(null);
    }

    public User getUserById (Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new NullPointerException("Such user is not"));
    }

    public boolean checkingNameAndPassword (String name, String password) {
        User user = userDao.findByUsername(name)
                .orElseThrow(() -> new NullPointerException("Such name of user no exist.. "));
        return user.getPassword().equals(password);
    }



}
