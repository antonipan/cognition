package ru.antonio.cognition.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;
import ru.antonio.cognition.models.Student;
import ru.antonio.cognition.models.Subject;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.services.StudentServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(StudentProfileController.class)
public class StudentProfileControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentServiceImpl studentService;

    @Autowired
    ObjectMapper objectMapper;

    private WebClient webClient;


    @BeforeEach
    void setup(WebApplicationContext context) {
        webClient = MockMvcWebClientBuilder
                .webAppContextSetup(context)
                .build();
    }

    @AfterEach
    public void closed () {
        webClient.close();
    }

    @Test
    void showMyProfileTest () throws Exception {
        Student student = new Student("igor", 1, "9A", 4.4);
        student.setId(1L);

        Mockito.when(studentService.getStudentById(1L)).thenReturn(student);

        mvc.perform(get("/students/{studentId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(view().name("student/studentProfile"))
//                .andExpect(jsonPath("$.username").value("null"))
//                .andExpect(jsonPath("$.grade").value("9A"))
                .andExpect(model().attribute("pass", studentService.getStudentById(1L).getPassable()));

    }

    @Test
    void updateMyProfileTest () throws Exception {
        Student newStudent = new Student("inga", 2, "7A", 5.0);
        newStudent.setId(1L);
        Mockito.when(studentService.updateStudent(1L, newStudent)).thenReturn(newStudent);

        mvc.perform(put("/students/{studentId}", studentService.updateStudent(1L, newStudent))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newStudent)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void findTeacherByNameTest () throws Exception {
        Student newStudent = new Student("inga", 2, "7A", 5.0);
        newStudent.setId(1L);
        Teacher teacher = new Teacher();
        teacher.setName("igor");
        teacher.setId(3L);
        Set<Subject> subjects = new HashSet<>();
        subjects.add(new Subject("h"));
        teacher.setSubjects(subjects);
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(teacher);

        Mockito.when(studentService.findTeacherByName("igor")).thenReturn(teacherList);

    }



}
