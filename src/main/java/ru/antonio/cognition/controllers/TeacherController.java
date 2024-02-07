package ru.antonio.cognition.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.antonio.cognition.model.Teacher;
import ru.antonio.cognition.services.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping()
    public List<Teacher> addTeacher(@RequestBody List<Teacher> teacher){
        return teacherService.saveAllTeacher(teacher);
    }

    @GetMapping
    public String getAllTeachers (Model model){
        model.addAttribute("teachers", teacherService.getListTeachers());
        return "teachers";
    }

    @GetMapping("/sort/{paramSorted}")
    public String getSorted (Model model, @PathVariable String paramSorted) {
        if (paramSorted.equals("name")) {
            model.addAttribute("teachers", teacherService.sortedTeachersByName());
            return "teachers";
        } else if (paramSorted.equals("experience")) {
            model.addAttribute("teachers", teacherService.sortedTeachersByExperience());
            return "teachers";
        } else if (paramSorted.equals("subject")) {
            model.addAttribute("teachers", teacherService.sortedTeachersBySubject());
            return "teachers";
        } else {
            return "";
        }
    }

    @GetMapping("/filter/{experience}")
    public String getByExperience (Model model, @PathVariable int experience){
         model.addAttribute("teachers", teacherService.getListByExperience(experience));
        return "teachers";
    }

    @GetMapping("/subjects/{subject}")
    public String getBySubject(@PathVariable String subject, Model model) {
        model.addAttribute("teachers", teacherService.getListBySubjects(subject));
        return "teachers";
    }

    @GetMapping("/{id}")
    public String getByIdTeacher(@PathVariable Long id, Model model){
        model.addAttribute("teachers", teacherService.getTeacherById(id));
        return "teacherProfile";
    }

    @PutMapping("/{id}")
    public String updateByExperience(@PathVariable Long id, @RequestBody int experience, Model model) {
        model.addAttribute("", teacherService.updateTeacherByExperience(id, experience));
        return "teacherProfile";
    }

    @DeleteMapping("/{id}")
    public String deleteTeacherById (@PathVariable Long id) {
        teacherService.deleteById(id);
        return "Объект успешно удалён";
    }
}
