package ru.antonio.cognition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.antonio.cognition.models.Student;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.models.User;
import ru.antonio.cognition.repositories.StudentDao;
import ru.antonio.cognition.repositories.TeacherDao;
import ru.antonio.cognition.repositories.UserDao;

@SpringBootApplication
public class CognitionApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context
				= SpringApplication.run(CognitionApplication.class, args);
		UserDao<Teacher> teacherDao = context.getBean(TeacherDao.class);
		UserDao<Student> userDao = context.getBean(StudentDao.class);
	}
}

// TODO: 07.02.2024 Добавить документацию 
// TODO: 07.02.2024 Сделать таблицы хранения пользователей, ролей и их соответствия
// TODO: 07.02.2024 сделать связь таблиц на уровне кода.
