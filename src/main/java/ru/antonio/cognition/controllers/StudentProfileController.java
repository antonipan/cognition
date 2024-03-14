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

    @RequestMapping(value = "/all-teach/findByName", method = RequestMethod.POST)
    public String findTeacherByName (@RequestBody String name, Model model){
        model.addAttribute("teachers", studentService.findTeacherByName(name));
        return "teacher/all-teachers";
    }

    @RequestMapping(value = "/my-teachers", method = RequestMethod.GET)
    public String showMyTeachers (@PathVariable Long studentId,
                                  Model model) {
        model.addAttribute("teachers", studentService.getStudentById(studentId).getTeachers());
        return "teacher/all-teachers";
    }

    @RequestMapping(value = "/all-quest/{questId}", method = RequestMethod.GET)
    public String openMethodic (@PathVariable Long questId, Model model) {
        model.addAttribute("questionnaire", studentService.showQuestionnaire(questId));
        return "questionnaire/questionnaireProfile";
    }

    @RequestMapping(value = "/all-quest/{questId}", method = RequestMethod.POST)
    public String goToMethodic (@PathVariable Long studentId,
                                @PathVariable Long questId,
                                @RequestBody Questionnaire questionnaire) {
        studentService.passTesting(studentId, questId, questionnaire);
        return "student/studentProfile";
    }
}
