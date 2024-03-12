package ru.antonio.cognition.controllers;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.services.TeacherServiceImpl;

import java.util.List;

public class AdminController {

//    private MeterRegistry meterRegistry;
//    private TeacherServiceImpl teacherService;
//
//
//    @GetMapping("/getAllTeachers")
////    @PreAuthorize("hasAuthority('ROLE_TEACH')")
//    public String getAllTeachers (Model model){
//        meterRegistry.counter("counter-getteach").increment();
//        model.addAttribute("teachers", teacherService.getListTeachers());
//        return "teachers";
//    }
//
////    @GetMapping("/sort/{paramSorted}")
////    public String getSorted (Model model, @PathVariable String paramSorted) {
////        if (paramSorted.equals("name")) {
////            model.addAttribute("teachers", teacherService.sortedTeachersByName());
////            return "teachers";
////        } else if (paramSorted.equals("experience")) {
////            model.addAttribute("teachers", teacherService.sortedTeachersByExperience());
////            return "teachers";
////        } else if (paramSorted.equals("subject")) {
////            model.addAttribute("teachers", teacherService.sortedTeachersBySubject());
////            return "teachers";
////        } else {
////            return "";
////        }
////    }
//
//    @GetMapping("/filter/{experience}")
//    public String getByExperience (Model model, @PathVariable int experience){
//        model.addAttribute("teachers", teacherService.getListByExperience(experience));
//        return "teachers";
//    }
//
////    @GetMapping("/subjects/{subject}")
//////    @PreAuthorize("hasAuthority('teacher')")
////    public String getBySubject(@PathVariable String subject, Model model) {
////        model.addAttribute("teachers", teacherService.getListBySubjects(subject));
////        return "teachers";
////    }
//
//    @PostMapping()
//    public List<Teacher> addTeacher(@RequestBody List<Teacher> teacher){
//        return teacherService.saveAllTeacher(teacher);
//    }
}
