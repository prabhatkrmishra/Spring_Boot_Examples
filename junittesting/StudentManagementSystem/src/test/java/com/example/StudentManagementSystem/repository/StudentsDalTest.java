package com.example.StudentManagementSystem.repository;

import com.example.StudentManagementSystem.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

// ── Spring Boot 4.x: @DataJpaTest moved to the data-jpa module ───────────────
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * StudentsDalTest
 *
 * <p>Repository slice tests for {@link StudentsDal} running against H2 in-memory.
 *
 * <p>Key change for Spring Boot 4.1.0:
 * <ul>
 *   <li>{@code @DataJpaTest} is now in package
 *       {@code org.springframework.boot.data.jpa.test.autoconfigure}
 *       (starter: {@code spring-boot-starter-data-jpa-test}).</li>
 * </ul>
 *
 * <p>{@code @DataJpaTest} loads only the JPA slice (entities, repositories,
 * Hibernate) and replaces the configured datasource with H2. Each test runs
 * in a transaction that is rolled back automatically after the method completes,
 * so each test starts from a clean state.
 */
@DataJpaTest
class StudentsDalTest {

    @Autowired
    private StudentsDal studentsDal;

    private Student student;

    /**
     * Creates a clean sample {@link Student} before each test.
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

    // ── save ─────────────────────────────────────────────────────────────────

    /**
     * Verifies a student can be saved and returns the correct sid.
     */
    @Test
    @DisplayName("save: should persist student and return entity with correct sid")
    void save_ShouldPersistStudentAndReturnSavedEntity() {

        Student saved = studentsDal.save(student);

        assertThat(saved).isNotNull();
        assertThat(saved.getSid()).isEqualTo(1);
        assertThat(saved.getName()).isEqualTo("John Doe");
        assertThat(saved.getCourse()).isEqualTo("Computer Science");
    }

    // ── findById ─────────────────────────────────────────────────────────────

    /**
     * Verifies findById returns the correct student after it has been saved.
     */
    @Test
    @DisplayName("findById: should return student when it exists")
    void findById_ShouldReturnStudentWhenExists() {

        studentsDal.save(student);

        Optional<Student> found = studentsDal.findById(1);

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("John Doe");
        assertThat(found.get().getEmailId()).isEqualTo("john.doe@example.com");
    }

    /**
     * Verifies findById returns empty Optional for an unknown ID.
     */
    @Test
    @DisplayName("findById: should return empty Optional when student not found")
    void findById_ShouldReturnEmptyWhenNotFound() {

        Optional<Student> found = studentsDal.findById(999);

        assertThat(found).isEmpty();
    }

    // ── findAll ──────────────────────────────────────────────────────────────

    /**
     * Verifies findAll returns all persisted students.
     */
    @Test
    @DisplayName("findAll: should return all persisted students")
    void findAll_ShouldReturnAllStudents() {

        Student second = new Student(2, "Jane Smith", 22,
                "Mathematics", "456 Elm St", "jane@example.com", "8888888888");

        studentsDal.save(student);
        studentsDal.save(second);

        List<Student> all = studentsDal.findAll();

        assertThat(all).hasSize(2);
    }

    /**
     * Verifies findAll returns an empty list when the repository is empty.
     */
    @Test
    @DisplayName("findAll: should return empty list when no students saved")
    void findAll_ShouldReturnEmptyListWhenNoStudentsSaved() {

        List<Student> all = studentsDal.findAll();

        assertThat(all).isEmpty();
    }

    // ── existsById ───────────────────────────────────────────────────────────

    /**
     * Verifies existsById returns true when the student has been saved.
     */
    @Test
    @DisplayName("existsById: should return true when student exists")
    void existsById_ShouldReturnTrueWhenStudentExists() {

        studentsDal.save(student);

        assertThat(studentsDal.existsById(1)).isTrue();
    }

    /**
     * Verifies existsById returns false for an ID that was never saved.
     */
    @Test
    @DisplayName("existsById: should return false when student does not exist")
    void existsById_ShouldReturnFalseWhenStudentNotFound() {

        assertThat(studentsDal.existsById(999)).isFalse();
    }

    // ── deleteById ───────────────────────────────────────────────────────────

    /**
     * Verifies deleteById removes the student so subsequent lookups return empty.
     */
    @Test
    @DisplayName("deleteById: should remove student so it can no longer be found")
    void deleteById_ShouldRemoveStudent() {

        studentsDal.save(student);

        studentsDal.deleteById(1);

        assertThat(studentsDal.existsById(1)).isFalse();
        assertThat(studentsDal.findById(1)).isEmpty();
    }
}
