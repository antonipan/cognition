package ru.antonio.cognition.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Student;

@Controller
@RequestMapping("/students/{studentId}")
public class StudentProfileController {


    public String showMyProfile(){
        return "";
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

    public String showMySubjects (@PathVariable Long studentId) {
        return "";
    }

    public String showMyQuestionnaires (@PathVariable Long student) {
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
