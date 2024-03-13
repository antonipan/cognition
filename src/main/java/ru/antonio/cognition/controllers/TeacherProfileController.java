package ru.antonio.cognition.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Subject;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.services.TeacherServiceImpl;

@Controller
@RequestMapping("/teachers/{teacherId}")
public class TeacherProfileController {

    private TeacherServiceImpl teacherService;

    @Autowired
    public TeacherProfileController(TeacherServiceImpl teacherService) {
        this.teacherService = teacherService;
    }


    // Работа с профилем.
    /**
     *
     * @param teacherId
     * @param model
     * @return
     */
    @GetMapping()
    public String getByIdTeacher(@PathVariable Long teacherId, Model model){
        model.addAttribute("teacher", teacherService.getTeacherById(teacherId));
        return "teacher/teacherProfile";
    }



    @PutMapping()
    public String updateTeacher(@PathVariable Long teacherId, @RequestBody Teacher teacher, Model model) {
        model.addAttribute("teacher", teacherService.updateTeacher(teacherId, teacher));
        return "teacher/teacherProfile";
    }

    @DeleteMapping()
    public String deleteTeacherById (@PathVariable Long teacherId) {
        teacherService.deleteTeacherById(teacherId);
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
        model.addAttribute("subjects", teacherService.getAllSubjects());
        return "subject/subjects";
    }

    /**
     * Создаёт новый предмет.
     * @param subject
     * @param model
     * @return
     */
    @RequestMapping(value = "/subjects", method = RequestMethod.POST)
    public String createSubject (@RequestBody Subject subject, Model model) {
        model.addAttribute("subjects", teacherService.createSubject(subject));
        return "subject/all-subjects";
    }

    /**
     * Добавляет предмет учителю
     * @param teacherId
     * @param subjectId
     * @return
     */
    @RequestMapping(value = "/subjects/{subjectId}", method = RequestMethod.PUT)
    public String addSubjectToTeacher (@PathVariable Long teacherId,
                                       @PathVariable Integer subjectId,
                                       Model model) {
        Subject subject = teacherService.getSubjectById(subjectId);
        model.addAttribute("teacher", teacherService.updateTeacher(teacherId, subject));
        return "teacher/teacherProfile";
    }

    /**
     * Показывает, какие у данного учителя есть предметы.
     * @param model
     * @return
     */
    @RequestMapping(value = "/my-subjects", method = RequestMethod.GET)
    public String getTeacherSubjects (@PathVariable Long teacherId, Model model) {
        model.addAttribute("subjects", teacherService.getMySubjects(teacherId));
        return "subject/my-subjects";
    }


    @RequestMapping(value = "/my-subjects/{subjectId}", method = RequestMethod.DELETE)
    public String deleteMySubject(@PathVariable Long teacherId, @PathVariable Integer subjectId, Model model) {
        teacherService.deleteSubjectFromTeacherList(teacherId, subjectId);
        model.addAttribute("subjects", teacherService.getMySubjects(teacherId));
        return "subject/my-subjects";
    }

    // WORK WITH QUESTIONNAIRES


    @RequestMapping(value = "/my-quest", method = RequestMethod.GET)
    public String showMyQuestionnaires (@PathVariable Long teacherId, Model model) {
        model.addAttribute("questionnaires", teacherService.getMyQuestionnaires(teacherId));
        model.addAttribute("teacher", teacherService.getTeacherById(teacherId));
        return "questionnaire/my-questionnaires";
    }

    @RequestMapping(value = "/my-quest", method = RequestMethod.POST)
    public Questionnaire createQuestToMyList (@PathVariable Long teacherId,
                                       @RequestBody Questionnaire questionnaire
                                       ) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        questionnaire.setAuthor(teacher);
        teacherService.saveMyQuestionnaire(questionnaire);
        return questionnaire;
    }

    @RequestMapping(value = "/my-quest/{questId}", method = RequestMethod.PUT)
    public String updateMyQuestionnaire(@PathVariable Long questId,
                                        @RequestBody Questionnaire questionnaire,
                                        Model model) {
        model.addAttribute("questionnaire", teacherService.updateMyQuestionnaire(questId, questionnaire));
        return "questionnaire/questionnaireProfile";
    }

    @RequestMapping(value = "/my-quest", method = RequestMethod.DELETE)
    public String deleteQuestFromMyList (@PathVariable Long teacherId,
                                         @RequestParam Long questId,
                                         Model model) {
        model.addAttribute("questionnaires", teacherService.deleteQuestFromMyList(teacherId, questId));
        return "questionnaire/my-questionnaires";
    }

    @RequestMapping(value = "/all-quest", method = RequestMethod.GET)
    public String showAllQuestionnaire () {
        return "";
    }

    @RequestMapping(value = "/all-quest/{questId}", method = RequestMethod.GET)
    public String showProfileQuestionnaire (@PathVariable Long questId) {
        return "";
    }

    @RequestMapping(value = "/all-quest/{questId}", method = RequestMethod.PUT)
    public String addQuestToTeacher (@PathVariable Long teacherId,
                                     @PathVariable Long questId,
                                     Model model) {
        Questionnaire questionnaire = teacherService.getQuestionnaireById(questId);
        model.addAttribute("questionnaires", teacherService.updateTeacher(teacherId, questionnaire));
        return "questionnaire/my-questionnaires";
    }

    @RequestMapping(value = "/all-quest/{questId}", method = RequestMethod.DELETE)
    public String deleteQuestionnaireFromApp (@PathVariable Long questId) {
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

    @RequestMapping(value = "/all-quest/find/byAuthor", method = RequestMethod.GET)
    public String findQuestByAuthor (@RequestBody String author) {
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
    public String showMyStudents (@PathVariable Long teacherId) {
        return "";
    }

    @RequestMapping(value = "/my-students/{studentId}", method = RequestMethod.GET)
    public String showProfileStudent (@PathVariable Long teacherId,
                                      @PathVariable Long studentId) {
        return "";
    }

    @RequestMapping(value = "/my-quest/{questId}/{studentId}", method = RequestMethod.POST)
    public String giveToStudentMyMethodic(@PathVariable Long teacherId,
                                          @PathVariable Long questId,
                                          @PathVariable Long studentId) {
        return "";
    }

    @RequestMapping(value = "/my-students/{studentId}", method = RequestMethod.DELETE)
    public String deleteStudentFromMyList (@PathVariable Long studentId) {
        return "";
    }
}
