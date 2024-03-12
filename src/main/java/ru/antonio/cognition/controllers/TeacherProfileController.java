package ru.antonio.cognition.controllers;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.antonio.cognition.models.Subject;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.services.SubjectService;
import ru.antonio.cognition.services.TeacherServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/teachers/{id}")
public class TeacherProfileController {

    private TeacherServiceImpl teacherService;
    private SubjectService subjectService;
    private MeterRegistry meterRegistry;

    @Autowired
    public TeacherProfileController(TeacherServiceImpl teacherService,
                                    SubjectService subjectService,
                                    MeterRegistry meterRegistry) {
        this.teacherService = teacherService;
        this.subjectService = subjectService;
        this.meterRegistry = meterRegistry;
    }


    // Работа с профилем.
    /**
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping()
    public String getByIdTeacher(@PathVariable Long id, Model model){
        model.addAttribute("teacher", teacherService.getTeacherById(id));
        return "teacherProfile";
    }

    @PostMapping()
    public List<Teacher> addTeacher(@RequestBody List<Teacher> teacher){
        return teacherService.saveAllTeacher(teacher);
    }

    @PutMapping()
    public String updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher, Model model) {
        model.addAttribute("teacher", teacherService.updateTeacher(id, teacher));
        return "teacherProfile";
    }

    @DeleteMapping()
    public String deleteTeacherById (@PathVariable Long id) {
        teacherService.deleteById(id);
        return "redirect:/reg";
    }

    // Работа с предметами.
    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    public String showAllSubjects (Model model) {
        model.addAttribute("subjects", subjectService.getAllSubject());
        return "subjects";
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.POST)
    public String createSubject (@RequestBody Subject subject, Model model) {
        model.addAttribute("subjects", subjectService.createSubject(subject));
        return "subjects";
    }

    @RequestMapping(value = "/subjects/{subId}", method = RequestMethod.POST)
    public String addSubjectToTeacher (@PathVariable Long id, @PathVariable Integer subId) {
        Subject subject = subjectService.getSubjectById(subId);
        teacherService.updateTeacher(id, subject);
        return "teacherProfile";
    }

    @RequestMapping(value = "/mysubjects", method = RequestMethod.GET)
    public String getTeacherSubjects (Model model) {
//        model.addAttribute("subject", teacherService.get)
        return "subjects";
    }

}
