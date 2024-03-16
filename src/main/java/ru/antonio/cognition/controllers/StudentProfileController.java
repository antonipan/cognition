package ru.antonio.cognition.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Student;
import ru.antonio.cognition.services.StudentServiceImpl;

/**
 * Класс контроллера, который управлет студентами.
 */
@Controller
@RequestMapping("/students/{studentId}")
public class StudentProfileController {
    /**
     * Сервис студентов.
     */
    private StudentServiceImpl studentService;

    /**
     *
     * @param studentService - сервис управления учеником.
     */
    @Autowired
    public StudentProfileController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    /**
     * GET-метод показывает профиль студента
     * @param studentId - id студента.
     * @param model - модель отображения профиля.
     * @return - страница отображения профиля "student/studentProfile
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showMyProfile(@PathVariable Long studentId, Model model){
        Student student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        model.addAttribute("noPass", student.getNotPassable());
        model.addAttribute("pass", student.getPassable());

        return "student/studentProfile";
    }

    /**
     * PUT-метод обновляет студента.
     * @param studentId - ID студента.
     * @param student - Объект студента.
     * @param model - модель отображения страницы профиля ученика
     * @return - страница отображения профиля студента "student/studentProfile"
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String updateMyProfile (@PathVariable Long studentId,
                                   @RequestBody Student student,
                                   Model model) {
        Student oldStudent = studentService.updateStudent(studentId, student);
        model.addAttribute("student", oldStudent);
        model.addAttribute("noPass", oldStudent.getNotPassable());
        model.addAttribute("pass", oldStudent.getPassable());
        return "student/studentProfile";
    }

    /**
     * POST-метод ищет учителя по имени
     * @param name - имя учителя
     * @param model - модель отображения списка учителей.
     * @return - страницу списка учителей "teacher/all-teachers"
     */
    @RequestMapping(value = "/all-teach/findByName", method = RequestMethod.POST)
    public String findTeacherByName (@RequestBody String name, Model model){
        model.addAttribute("teachers", studentService.findTeacherByName(name));
        return "teacher/all-teachers";
    }

    /**
     * GET-метод показывает список учителей у данного студента.
     * @param studentId - ID студента.
     * @param model - модель отображения списка моих учителей
     * @return - страница отображения списка учителей данного студента  "teacher/all-teachers"
     */
    @RequestMapping(value = "/my-teachers", method = RequestMethod.GET)
    public String showMyTeachers (@PathVariable Long studentId,
                                  Model model) {
        model.addAttribute("teachers", studentService.getStudentById(studentId).getTeachers());
        return "teacher/all-teachers";
    }

    /**
     * GET-метод просмотра определённой методики по её идентификатору
     * @param questId - ID методики
     * @param model - смодель отображения профиля меодики
     * @return - страница отображения профиля методики "questionnaire/questionnaireProfile"
     */
    @RequestMapping(value = "/all-quest/{questId}", method = RequestMethod.GET)
    public String openMethodic (@PathVariable Long questId, Model model) {
        model.addAttribute("questionnaire", studentService.showQuestionnaire(questId));
        return "questionnaire/questionnaireProfile";
    }

    /**
     * POST-метод для прохождения методики определённым студентом по указанному ID
     * @param studentId - ID студента
     * @param questId - ID методики
     * @param questionnaire - методика с ответами, которую отправляет студент.
     * @return - страница профиля студента "student/studentProfile"
     */
    @RequestMapping(value = "/all-quest/{questId}", method = RequestMethod.POST)
    public String goToMethodic (@PathVariable Long studentId,
                                @PathVariable Long questId,
                                @RequestBody Questionnaire questionnaire) {
        studentService.passTesting(studentId, questId, questionnaire);
        return "student/studentProfile";
    }
}
