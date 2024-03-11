package ru.antonio.cognition.controllers;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.services.TeacherService;
import ru.antonio.cognition.services.TeacherServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private TeacherServiceImpl teacherService;

    private MeterRegistry meterRegistry;

    @Autowired
    public TeacherController(TeacherServiceImpl teacherService, MeterRegistry meterRegistry) {
        this.teacherService = teacherService;
        this.meterRegistry = meterRegistry;
    }

//    @PostMapping()
//    public List<Teacher> addTeacher(@RequestBody List<Teacher> teacher){
//        return teacherService.saveAllTeacher(teacher);
//    }

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_TEACH')")
    public String getAllTeachers (Model model){
        meterRegistry.counter("counter-getteach").increment();
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
//    @PreAuthorize("hasAuthority('teacher')")
    public String getBySubject(@PathVariable String subject, Model model) {
        model.addAttribute("teachers", teacherService.getListBySubjects(subject));
        return "teachers";
    }

    @GetMapping("/{id}")
    public String getByIdTeacher(@PathVariable Long id, Model model){
        model.addAttribute("teacher", teacherService.getTeacherById(id));
        return "teacherProfile";
    }

//    @PutMapping("/{id}")
//    public String updateByExperience(@PathVariable Long id, @RequestBody int experience, Model model) {
//        model.addAttribute("", teacherService.updateTeacherByExperience(id, experience));
//        return "teacherProfile";
//    }

    @DeleteMapping("/{id}")
    public String deleteTeacherById (@PathVariable Long id) {
        teacherService.deleteById(id);
        return "Объект успешно удалён";
    }
}
