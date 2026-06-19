package com.example.StudentManagementSystem.controller;

import com.example.StudentManagementSystem.model.Student;
import com.example.StudentManagementSystem.service.StudentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

// ── Spring Boot 4.x: @WebMvcTest moved to the webmvc module ──────────────────
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

// ── Jackson 3 (Spring Boot 4.x): ObjectMapper is now in tools.jackson.databind ─
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * StudentsControllerTest
 *
 * <p>Web-layer slice tests for {@link StudentsController} using MockMvc.
 * Only the MVC layer is loaded — no full Spring context, no real database.
 *
 * <p>Key changes for Spring Boot 4.1.0 / Jackson 3:
 * <ul>
 *   <li>{@code @WebMvcTest} is now in package
 *       {@code org.springframework.boot.webmvc.test.autoconfigure}
 *       (starter: {@code spring-boot-starter-webmvc-test}).</li>
 *   <li>{@code ObjectMapper} is now {@code tools.jackson.databind.ObjectMapper}
 *       (Jackson 3 package rebrand from {@code com.fasterxml.jackson.databind}).</li>
 *   <li>{@code writeValueAsString} now throws {@code JacksonException}
 *       (a {@code RuntimeException}), so no checked-exception handling is needed
 *       beyond the test method's existing {@code throws Exception}.</li>
 * </ul>
 */
@WebMvcTest(StudentsController.class)
class StudentsControllerTest {

    // ── MockMvc & helpers ─────────────────────────────────────────────────────

    /** Provided by @WebMvcTest – performs HTTP requests in tests. */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Jackson 3 ObjectMapper auto-configured by Spring Boot.
     * Import is {@code tools.jackson.databind.ObjectMapper} in Spring Boot 4.x.
     */
    @Autowired
    private ObjectMapper objectMapper;

    // ── Mocks ─────────────────────────────────────────────────────────────────

    /** Mocked service layer – injected into the controller under test. */
    @MockitoBean
    private StudentsService studentsService;

    // ── Fixtures ──────────────────────────────────────────────────────────────

    private Student student;

    /**
     * Builds a shared {@link Student} fixture before each test.
     */
    @BeforeEach
    void setUp() {
        student = new Student(
                1,
                "John Doe",
                20,
                "Computer Science",
                "123 Main Street, New York",
                "john.doe@example.com",
                "9999999999"
        );
    }

    // ── GET /students/getStudents ─────────────────────────────────────────────

    /**
     * Verifies GET /students/getStudents returns HTTP 200 and a JSON array
     * containing the mocked student.
     */
    @Test
    @DisplayName("GET /students/getStudents: should return 200 with list of students")
    void getStudents_ShouldReturn200WithStudentList() throws Exception {

        when(studentsService.getStudents()).thenReturn(List.of(student));

        mockMvc.perform(get("/students/getStudents"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].sid").value(1))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].course").value("Computer Science"));
    }

    /**
     * Verifies GET /students/getStudents returns HTTP 200 and an empty JSON array
     * when the service returns no students.
     */
    @Test
    @DisplayName("GET /students/getStudents: should return 200 with empty list")
    void getStudents_ShouldReturn200WithEmptyList() throws Exception {

        when(studentsService.getStudents()).thenReturn(List.of());

        mockMvc.perform(get("/students/getStudents"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0));
    }

    // ── POST /students/addStudent ─────────────────────────────────────────────

    /**
     * Verifies POST /students/addStudent returns HTTP 200 and the saved student JSON.
     */
    @Test
    @DisplayName("POST /students/addStudent: should return 200 with saved student")
    void addStudent_ShouldReturn200WithSavedStudent() throws Exception {

        when(studentsService.addStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/students/addStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.sid").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.emailId").value("john.doe@example.com"));
    }

    /**
     * Verifies POST /students/addStudent without Content-Type returns HTTP 415.
     */
    @Test
    @DisplayName("POST /students/addStudent: should return 415 when Content-Type missing")
    void addStudent_ShouldReturn415WhenContentTypeMissing() throws Exception {

        mockMvc.perform(post("/students/addStudent")
                        .content(objectMapper.writeValueAsString(student)))
                .andDo(print())
                .andExpect(status().isUnsupportedMediaType());
    }

    // ── DELETE /students/deleteStudent/{sid} ──────────────────────────────────

    /**
     * Verifies DELETE returns 200 and success message when student exists.
     */
    @Test
    @DisplayName("DELETE /students/deleteStudent/{sid}: should return 200 with success message")
    void deleteStudent_ShouldReturn200WithSuccessMessage() throws Exception {

        when(studentsService.deleteStudent(1)).thenReturn(true);

        mockMvc.perform(delete("/students/deleteStudent/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Student SID: 1"));
    }

    /**
     * Verifies DELETE returns "Student does not exist!" when student is not found.
     */
    @Test
    @DisplayName("DELETE /students/deleteStudent/{sid}: should return not-found message")
    void deleteStudent_ShouldReturnNotFoundMessage() throws Exception {

        when(studentsService.deleteStudent(99)).thenReturn(false);

        mockMvc.perform(delete("/students/deleteStudent/99"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Student does not exist!"));
    }
}
