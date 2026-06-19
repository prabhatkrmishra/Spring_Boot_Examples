package com.example.StudentManagementSystem.integration;

import com.example.StudentManagementSystem.model.Student;
import com.example.StudentManagementSystem.repository.StudentsDal;
import com.example.StudentManagementSystem.service.StudentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

// ── Spring Boot 4.x: @AutoConfigureMockMvc moved to the webmvc-test module ───
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

// ── Jackson 3 (Spring Boot 4.x): ObjectMapper is now tools.jackson.databind ──
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * StudentManagementIntegrationTest
 *
 * <p>End-to-end integration tests that load the FULL Spring application context
 * and wire the controller → service → repository stack against H2 in-memory
 * (configured in {@code src/test/resources/application.yml}).
 *
 * <p>Key changes for Spring Boot 4.1.0:
 * <ul>
 *   <li>{@code @SpringBootTest} no longer auto-configures MockMvc — you must
 *       add {@code @AutoConfigureMockMvc} explicitly.</li>
 *   <li>{@code @AutoConfigureMockMvc} is now in package
 *       {@code org.springframework.boot.webmvc.test.autoconfigure}
 *       (starter: {@code spring-boot-starter-webmvc-test}).</li>
 *   <li>{@code ObjectMapper} is {@code tools.jackson.databind.ObjectMapper}
 *       (Jackson 3). {@code writeValueAsString} now throws
 *       {@code JacksonException} (a {@code RuntimeException}), covered by
 *       the method-level {@code throws Exception}.</li>
 * </ul>
 */
@SpringBootTest
@AutoConfigureMockMvc
class StudentManagementIntegrationTest {

    // ── Infrastructure ────────────────────────────────────────────────────────

    /** Full-stack MockMvc configured by @AutoConfigureMockMvc. */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Jackson 3 ObjectMapper.
     * Import is {@code tools.jackson.databind.ObjectMapper} in Spring Boot 4.x.
     */
    @Autowired
    private ObjectMapper objectMapper;

    // ── Beans ─────────────────────────────────────────────────────────────────

    /** Real service bean from the application context (no mocks). */
    @Autowired
    private StudentsService studentsService;

    /** Real repository bean – used for direct DB assertions and cleanup. */
    @Autowired
    private StudentsDal studentsDal;

    // ── Setup ─────────────────────────────────────────────────────────────────

    /**
     * Clears the H2 database before each test to ensure full isolation.
     */
    @BeforeEach
    void setUp() {
        studentsDal.deleteAll();
    }

    // ── Context load ──────────────────────────────────────────────────────────

    /**
     * Smoke test: confirms the Spring application context loads correctly
     * and all beans are wired.
     */
    @Test
    @DisplayName("Application context should load successfully")
    void applicationContext_ShouldLoadSuccessfully() {

        assertThat(studentsService).isNotNull();
        assertThat(studentsDal).isNotNull();
    }

    // ── addStudent (via service) ───────────────────────────────────────────────

    /**
     * Verifies {@link StudentsService#addStudent(Student)} persists a record
     * all the way to H2.
     */
    @Test
    @DisplayName("addStudent (service): should persist student to H2 database")
    void addStudent_ShouldPersistToDatabase() {

        Student student = new Student(
                100,
                "Integration User",
                22,
                "Spring Boot",
                "456 Oak Avenue",
                "integration@example.com",
                "1234567890"
        );

        Student saved = studentsService.addStudent(student);

        assertThat(saved).isNotNull();
        assertThat(saved.getSid()).isEqualTo(100);
        assertThat(studentsDal.existsById(100)).isTrue();
    }

    // ── GET /students/getStudents ──────────────────────────────────────────────

    /**
     * Persists two students, then makes an HTTP GET and asserts the JSON array
     * contains both.
     */
    @Test
    @DisplayName("GET /students/getStudents: should return all students from database")
    void getStudents_ShouldReturnAllStudentsFromDatabase() throws Exception {

        studentsDal.save(new Student(1, "Alice", 21, "Physics",
                "789 Pine Rd", "alice@example.com", "7777777777"));
        studentsDal.save(new Student(2, "Bob", 23, "Chemistry",
                "321 Maple Ave", "bob@example.com", "6666666666"));

        mockMvc.perform(get("/students/getStudents"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    /**
     * Verifies GET returns an empty JSON array when the database has no records.
     */
    @Test
    @DisplayName("GET /students/getStudents: should return empty array when database empty")
    void getStudents_ShouldReturnEmptyArrayWhenDatabaseEmpty() throws Exception {

        mockMvc.perform(get("/students/getStudents"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    // ── POST /students/addStudent ──────────────────────────────────────────────

    /**
     * Sends a JSON student via POST and asserts both the response body and the
     * resulting database state.
     */
    @Test
    @DisplayName("POST /students/addStudent: should save student via HTTP and return JSON")
    void addStudent_ViaHttp_ShouldPersistAndReturnStudent() throws Exception {

        Student student = new Student(10, "Charlie", 24, "Biology",
                "555 Birch Ln", "charlie@example.com", "5555555555");

        mockMvc.perform(post("/students/addStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sid").value(10))
                .andExpect(jsonPath("$.name").value("Charlie"));

        assertThat(studentsDal.existsById(10)).isTrue();
    }

    // ── DELETE /students/deleteStudent/{sid} ───────────────────────────────────

    /**
     * Saves a student, deletes via HTTP, asserts the success message and that the
     * record is removed from the database.
     */
    @Test
    @DisplayName("DELETE /students/deleteStudent/{sid}: should delete and return success message")
    void deleteStudent_ShouldRemoveStudentFromDatabase() throws Exception {

        studentsDal.save(new Student(5, "Diana", 20, "Art",
                "100 Cedar St", "diana@example.com", "4444444444"));

        mockMvc.perform(delete("/students/deleteStudent/5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Student SID: 5"));

        assertThat(studentsDal.existsById(5)).isFalse();
    }

    /**
     * Verifies that deleting a non-existent student returns the not-found message.
     */
    @Test
    @DisplayName("DELETE /students/deleteStudent/{sid}: should return not-found for unknown sid")
    void deleteStudent_ShouldReturnNotFoundForUnknownSid() throws Exception {

        mockMvc.perform(delete("/students/deleteStudent/999"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Student does not exist!"));
    }

    // ── Full CRUD lifecycle ────────────────────────────────────────────────────

    /**
     * End-to-end test covering the complete lifecycle:
     * add → retrieve → delete → verify deletion.
     */
    @Test
    @DisplayName("Full CRUD lifecycle: add, retrieve, delete end-to-end")
    void fullCrudLifecycle_ShouldWorkEndToEnd() throws Exception {

        // 1. Add
        Student student = new Student(200, "Eve", 25, "Music",
                "77 Walnut Dr", "eve@example.com", "3333333333");

        mockMvc.perform(post("/students/addStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sid").value(200));

        // 2. Retrieve – should contain the newly added student
        mockMvc.perform(get("/students/getStudents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Eve"));

        // 3. Delete
        mockMvc.perform(delete("/students/deleteStudent/200"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted Student SID: 200"));

        // 4. Verify deletion
        List<Student> remaining = studentsService.getStudents();
        assertThat(remaining).isEmpty();
    }
}
