package ru.antonio.cognition.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Subject;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.services.TeacherServiceImpl;

/**
 * Класс управления учителем
 */
@Controller
@RequestMapping("/teachers/{teacherId}")
public class TeacherProfileController {
    /**
     * Сервис управления функциями учителя
     */
    private final TeacherServiceImpl teacherService;

    /**
     *
     * @param teacherService - сервис упралвения функции учителя
     */
    @Autowired
    public TeacherProfileController(TeacherServiceImpl teacherService) {
        this.teacherService = teacherService;
    }

    // Работа с профилем.
    /**
     *GET-метод отображает профиль учителя.
     * @param teacherId - ID учителя
     * @param model - модель отображения профиля учителя
     * @return - страницу профиля учителя "teacher/teacherProfile";
     */
    @GetMapping()
    public String getByIdTeacher(@PathVariable Long teacherId, Model model){
        model.addAttribute("teacher", teacherService.getTeacherById(teacherId));
        return "teacher/teacherProfile";
    }

    /**
     * PUT-метод обновляет учителя по новым данным учителя.
     * @param teacherId - ID учителя
     * @param teacher - новые данные учителя
     * @param model - модель отображения профиля учителя
     * @return - страница профиля учителя "teacher/teacherProfile"
     */
    @PutMapping()
    public String updateTeacher(@PathVariable Long teacherId, @RequestBody Teacher teacher, Model model) {
        model.addAttribute("teacher", teacherService.updateTeacher(teacherId, teacher));
        return "teacher/teacherProfile";
    }

    /**
     * DELETE-метод удаления учителя по его индентификатору
     * @param teacherId - ID учителя
     * @return - перебрасывает студента для регистрации
     */
    @DeleteMapping()
    public String deleteTeacherById (@PathVariable Long teacherId) {
        teacherService.deleteTeacherById(teacherId);
        return "redirect:/reg";
    }

    // Работа с предметами.

    /**
     * GET-метод Показывает все возможные предметы из базы данных.
     * @param model модель отображения списка предметод
     * @return страница списка предметов "subject/subjects"
     */
    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    public String showAllSubjects (Model model) {
        model.addAttribute("subjects", teacherService.getAllSubjects());
        return "subject/subjects";
    }

    /**
     * POST метод создаёт новый предмет.
     * @param subject - создаёт предмет
     * @param model модель отображения списка предметов базы данных
     * @return страница отображения списко всех предметов "subject/all-subjects"
     */
    @RequestMapping(value = "/subjects", method = RequestMethod.POST)
    public String createSubject (@RequestBody Subject subject, Model model) {
        model.addAttribute("subjects", teacherService.createSubject(subject));
        return "subject/all-subjects";
    }

    /**
     * PUT Добавляет предмет учителю
     * @param teacherId - ID учителя
     * @param subjectId ID предмета
     * @return страница профиля учителя "teacher/teacherProfile"
     */
    @RequestMapping(value = "/subjects/{subjectId}", method = RequestMethod.PUT)
    public String addSubjectToTeacher (@PathVariable Long teacherId,
                                       @PathVariable Integer subjectId,
                                       Model model) {
        Subject subject = teacherService.getSubjectById(subjectId);
        model.addAttribute("teacher",
                teacherService.updateTeacher(teacherId, subject));
        return "teacher/teacherProfile";
    }

    /**
     * GET-метод Показывает, какие у данного учителя есть предметы.
     * @param model модель отображения списка предметов учителя
     * @return страницу отображения предметов учителя "subject/my-subjects"
     */
    @RequestMapping(value = "/my-subjects", method = RequestMethod.GET)
    public String getTeacherSubjects (@PathVariable Long teacherId, Model model) {
        model.addAttribute("subjects", teacherService.getMySubjects(teacherId));
        return "subject/my-subjects";
    }

    /**
     * DELETE-метод удаляет предмет по его идентификатору
     * @param teacherId - ID учителя
     * @param subjectId - ID предмета
     * @param model модель отображения списка предметов учителя
     * @return страницу отображения предметов учителя "subject/my-subjects"
     */
    @RequestMapping(value = "/my-subjects/{subjectId}", method = RequestMethod.DELETE)
    public String deleteMySubject(@PathVariable Long teacherId, @PathVariable Integer subjectId, Model model) {
        teacherService.deleteSubjectFromTeacherList(teacherId, subjectId);
        model.addAttribute("subjects", teacherService.getMySubjects(teacherId));
        return "subject/my-subjects";
    }

    // WORK WITH QUESTIONNAIRES

    /**
     * GET-метод показывает все методики данного учителя
     * @param teacherId - ID учителя
     * @param model модель отображения списка методик данного учителя
     * @return страница отображения списка методик данного учителя "questionnaire/my-questionnaires"
     */
    @RequestMapping(value = "/my-quest", method = RequestMethod.GET)
    public String showMyQuestionnaires (@PathVariable Long teacherId, Model model) {
        model.addAttribute("questionnaires", teacherService.getMyQuestionnaires(teacherId));
        model.addAttribute("teacher", teacherService.getTeacherById(teacherId));
        return "questionnaire/my-questionnaires";
    }

    /**
     * POST-метод создания методики данным учителем
     * @param teacherId - ID учителя
     * @param questionnaire - новая методика
     * @param model модель отображения профиля созданной методики
     * @return страница профиля методики "questionnaire/questionnaireProfile"
     */
    @RequestMapping(value = "/my-quest", method = RequestMethod.POST)
    public String createQuestToMyList (@PathVariable Long teacherId,
                                     @RequestBody Questionnaire questionnaire,
                                       Model model) {
        model.addAttribute("questionnaire",
                teacherService.createQuestionnaire(questionnaire));
        return "questionnaire/questionnaireProfile";
    }

    /**
     * PUT-метод обновления моей методики
     * @param questId ID методики
     * @param questionnaire - новая методика
     * @param model модель отображения профиля созданной методики
     * @return страница профиля методики "questionnaire/questionnaireProfile"
     */
    @RequestMapping(value = "/my-quest/{questId}", method = RequestMethod.PUT)
    public String updateMyQuestionnaire(@PathVariable Long questId,
                                        @RequestBody Questionnaire questionnaire,
                                        Model model) {
        model.addAttribute("questionnaire", teacherService.updateMyQuestionnaire(questId, questionnaire));
        return "questionnaire/questionnaireProfile";
    }

    /**
     * DELETE-метод удаляет методику из списка учителя, но не удаляет её из базы данных.
     * @param teacherId - ID учителя
     * @param questId ID методики
     * @param model модель отображения списка методик данного учителя
     * @return страница отображения списка методик данного учителя "questionnaire/my-questionnaires"
     */
    @RequestMapping(value = "/my-quest", method = RequestMethod.DELETE)
    public String deleteQuestFromMyList (@PathVariable Long teacherId,
                                         @RequestParam Long questId,
                                         Model model) {
        model.addAttribute("questionnaires", teacherService.deleteQuestFromMyList(teacherId, questId));
        model.addAttribute("teacher", teacherService.getTeacherById(teacherId));
        return "questionnaire/my-questionnaires";
    }

    /**
     * GET-метод показывает все методики, имеющиеся в базе данных
     * @param model модель отображения списка всех методик
     * @return страница отображения всех методик "questionnaire/all-questionnaires"
     */
    @RequestMapping(value = "/all-quest", method = RequestMethod.GET)
    public String showAllQuestionnaire (Model model) {
        model.addAttribute("questionnaires", teacherService.getAllQuestionnaire());
        return "questionnaire/all-questionnaires";
    }

    /**
     * GET-метод просмотра профиля методики
     * @param teacherId - ID учителя
     * @param questId ID методики
     * @param model модель отображения профиля созданной методики
     * @return страница профиля методики "questionnaire/questionnaireProfile"
     */
    @RequestMapping(value = "/all-quest/{questId}", method = RequestMethod.GET)
    public String showProfileQuestionnaire (@PathVariable Long teacherId,
                                            @PathVariable Long questId,
                                            Model model) {
        model.addAttribute("questionnaire",
                teacherService.getQuestionnaireById(questId));
        model.addAttribute("teacher", teacherService.getTeacherById(teacherId));
        return "questionnaire/questionnaireProfile";
    }

    /**
     * PUT-метод добавления вопроса в данную методику.
     * @param teacherId - ID учителя
     * @param questId ID методики
     * @param model модель отображения списка методик данного учителя
     * @return страница отображения списка методик данного учителя "questionnaire/my-questionnaires"
     */
    @RequestMapping(value = "/all-quest/{questId}", method = RequestMethod.PUT)
    public String addQuestToTeacher (@PathVariable Long teacherId,
                                     @PathVariable Long questId,
                                     Model model) {
        Questionnaire questionnaire = teacherService.getQuestionnaireById(questId);
        model.addAttribute("questionnaires",
                teacherService.updateTeacher(teacherId, questionnaire));
        model.addAttribute("teacher",
                teacherService.getTeacherById(teacherId));
        return "questionnaire/my-questionnaires";
    }

    /**
     * GET-метод поиска методик по их названию
     * @param questName - название методики
     * @param model модель отображения списка найденных методик
     * @return страница отображения найденных методик "questionnaire/all-questionnaires"
     */
    @RequestMapping(value = "/all-quest/find/{questName}", method = RequestMethod.GET)
    public String findQuestByName (@PathVariable String questName, Model model) {
        model.addAttribute("questionnaires", teacherService.findQuestByName(questName));
        return "questionnaire/all-questionnaires";
    }

    //РАБОТА СО СТУДЕНТАМИ.

    /**
     * GET-метод показывает учителю список его студентов.
     * @param teacherId - ID учителя
     * @param model модель отображения списка учеников данного учителя
     * @return странца отображения списка учеников данного учителя "student/my-students"
     */
    @RequestMapping(value = "/my-students", method = RequestMethod.GET)
    public String showMyStudents (@PathVariable Long teacherId, Model model) {
        model.addAttribute("students", teacherService.getMyStudents(teacherId));
        return "student/my-students";
    }

    /**
     * GET-метод показывает учителю профиль ученика
     * @param teacherId - ID учителя
     * @param studentId ID студента
     * @return - метод не доработан
     */
    @RequestMapping(value = "/my-students/{studentId}", method = RequestMethod.GET)
    public String showProfileStudent (@PathVariable Long teacherId,
                                      @PathVariable Long studentId) {
        return "";
    }

    /**
     * POST-метод, по которому данный учитель может отправить данную методику
     * для данного ученика, чтобы он смог пройти её
     * @param teacherId - ID учителя
     * @param questId - ID ученика
     * @param studentId ID студента
     * @param model модель отображения списка методик данного учителя
     * @return страница отображения списка методик данного учителя "questionnaire/my-questionnaires"
     */
    @RequestMapping(value = "/my-quest/{questId}/{studentId}", method = RequestMethod.POST)
    public String giveToStudentMyMethodic(@PathVariable Long teacherId,
                                          @PathVariable Long questId,
                                          @PathVariable Long studentId,
                                          Model model) {
        teacherService.setQuestForStudent(questId, studentId);
        model.addAttribute("questionnaire",
                teacherService.getQuestionnaireById(questId));
        model.addAttribute("teacher", teacherService.getTeacherById(teacherId));
        return "questionnaire/my-questionnaires";
    }

    /**
     * DELETE-метод удаляет данного ученика из списка данного учителя
     * @param teacherId - ID учителя
     * @param studentId - ID студента
     * @param model - модель отображения списка студентов учителя
     * @return страница отображения списка студентов учителя "student/my-students"
     */
    @RequestMapping(value = "/my-students/{studentId}", method = RequestMethod.DELETE)
    public String deleteStudentFromMyList (@PathVariable Long teacherId,
                                           @PathVariable Long studentId,
                                           Model model) {
        model.addAttribute("students", teacherService.deleteStudentFromListTeacher(teacherId, studentId));
        return "student/my-students";
    }

    /**
     * GET-метод показывает список студентов данного учителя
     * @param model - модель отображения списка студентов учителя
     * @return страница отображения списка студентов учителя "student/my-students"
     */
    @RequestMapping(value = "/all-students", method = RequestMethod.GET)
    public String showAllStudents (Model model) {
        model.addAttribute("students", teacherService.getAllStudents());
        return "student/my-students";
    }

    /**
     * POST-метод добавляет данного ученика в список данного учителя по имени
     * @param teacherId - ID учителя
     * @param name - имя ученика
     * @param model - модель отображения списка студентов учителя
     * @return страница отображения списка студентов учителя "student/my-students"
     */
    @RequestMapping(value = "/all-students/{name}", method = RequestMethod.POST)
    public String addStudentToList (@PathVariable Long teacherId,
                                    @PathVariable String name,
                                    Model model) {
        model.addAttribute("students", teacherService.addStudentToList(teacherId, name));
        return "student/my-students";
    }


}
