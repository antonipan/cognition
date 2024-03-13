package ru.antonio.cognition.controllers;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Subject;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.services.SubjectService;
import ru.antonio.cognition.services.TeacherServiceImpl;

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

    /**
     * Показывает все возможные предметы из базы данных.
     * @param model
     * @return
     */
    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    public String showAllSubjects (Model model) {
        model.addAttribute("subjects", subjectService.getAllSubject());
        return "subjects";
    }

    /**
     * Создаёт новый предмет.
     * @param subject
     * @param model
     * @return
     */
    @RequestMapping(value = "/subjects", method = RequestMethod.POST)
    public String createSubject (@RequestBody Subject subject, Model model) {
        model.addAttribute("subjects", subjectService.createSubject(subject));
        return "subjects/all-subject";
    }

    /**
     * Добавляет предмет учителю
     * @param id
     * @param subjectId
     * @return
     */
    @RequestMapping(value = "/subjects/{subjectId}", method = RequestMethod.PUT)
    public String addSubjectToTeacher (@PathVariable Long id, @PathVariable Integer subjectId, Model model) {
        Subject subject = subjectService.getSubjectById(subjectId);
        model.addAttribute("teacher", teacherService.updateTeacher(id, subject));
        return "teacherProfile";
    }

    /**
     * Показывает, какие у данного учителя есть предметы.
     * @param model
     * @return
     */
    @RequestMapping(value = "/my-subjects", method = RequestMethod.GET)
    public String getTeacherSubjects (@PathVariable Long id, Model model) {
        model.addAttribute("subjects", teacherService.getMySubjects(id));
        return "subject/my-subjects";
    }

    /**
     * Учитель удаляет предмет из своего списка
     * @param id
     * @param subjectId
     * @param model
     * @return
     */
    @RequestMapping(value = "/my-subjects/{subjectId}", method = RequestMethod.DELETE)
    public String deleteMySubject(@PathVariable Long id, @PathVariable Integer subjectId, Model model) {
        teacherService.deleteSubjectFromTeacherList(id, subjectId);
        model.addAttribute("subjects", teacherService.getMySubjects(id));
        return "subject/my-subjects";
    }


    @RequestMapping(value = "/my-quest", method = RequestMethod.GET)
    public String showMyQuestionnaires (Model model) {
        return "";
    }

    @RequestMapping(value = "/my-quest", method = RequestMethod.POST)
    public String createQuestToMyList (Questionnaire questionnaire) {
        return "";
    }

    @RequestMapping(value = "/my-quest", method = RequestMethod.PUT)
    public String updateMyQuestionnaire(Long id, Questionnaire questionnaire) {
        return "";
    }

    @RequestMapping(value = "/my-quest", method = RequestMethod.DELETE)
    public String deleteQuestFromMyList (@PathVariable Long teacherId, @PathVariable Long questId) {
        return "";
    }

    @RequestMapping(value = "/all-quest", method = RequestMethod.GET)
    public String showAllQuestionnaire () {
        return "";
    }

    @RequestMapping(value = "/all-quest/{id}", method = RequestMethod.GET)
    public String showProfileQuestionnaire (@PathVariable Long id) {
        return "";
    }

    @RequestMapping(value = "/all-quest/{id}", method = RequestMethod.POST)
    public String addCurrentQuestionnaire (@PathVariable Long id) {
        return "";
    }

    @RequestMapping(value = "/all-quest/{id}", method = RequestMethod.DELETE)
    public String deleteQuestionnaireFromApp (@PathVariable Long id) {
        return "";
    }

    @RequestMapping(value = "/all-quest/find/byName", method = RequestMethod.GET)
    public String findQuestByName (@RequestBody String questName) {
        return "";
    }

    @RequestMapping(value = "/all-quest/find/bySubject", method = RequestMethod.GET)
    public String findQuestBySubject (@RequestBody String subject) {
        return "";
    }

    @RequestMapping(value = "/all-quest/sort/popul", method = RequestMethod.GET)
    public String sortedQuestionnaireByPopular() {
        return "";
    }

    @RequestMapping(value = "/all-quest/sort/author", method = RequestMethod.GET)
    public String sortedQuestionnaireByAuthor() {
        return "";
    }

    @RequestMapping(value = "/all-quest/sort/subject", method = RequestMethod.GET)
    public String sortedQuestionnaireBySubject () {
        return "";
    }

    //РАБОТА СО СТУДЕНТАМИ.

    @RequestMapping(value = "/my-students", method = RequestMethod.GET)
    public String showMyStudents () {
        return "";
    }

    @RequestMapping(value = "/my-students/{id}", method = RequestMethod.GET)
    public String showProfileStudent (@PathVariable Long id) {
        return "";
    }

    @RequestMapping(value = "/my-students/{id}", method = RequestMethod.POST)
    public String giveToStudentMyMethodic(@PathVariable Long studentId) {
        return "";
    }

    @RequestMapping(value = "/my-students/{id}", method = RequestMethod.DELETE)
    public String deleteStudentFromMyList (@PathVariable Long id) {
        return "";
    }







}
