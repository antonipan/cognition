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

@Controller
@RequestMapping("/students/{studentId}")
public class StudentProfileController {

    private StudentServiceImpl studentService;

    @Autowired
    public StudentProfileController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showMyProfile(@PathVariable Long studentId, Model model){
        Student student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        model.addAttribute("noPass", student.getNotPassable());
        model.addAttribute("pass", student.getPassable());

        return "student/studentProfile";
    }

    public String updateMyProfile (@PathVariable Long studentId, @RequestBody Student student) {
        return "";
    }

    public String findTeacherByName (@RequestBody String name){
        return "";
    }

    public String findTeacherBySubject (@RequestBody String subject) {
        return "";
    }

    public String showMyTeachers (@PathVariable Long studentId) {
        return "";
    }

    public String openMethodic (@PathVariable Long id) {
        return "";
    }

    public String goToMethodic (@PathVariable Long studentId,
                                @PathVariable Long questId,
                                @RequestBody Questionnaire questionnaire) {
        return "";
    }
}
