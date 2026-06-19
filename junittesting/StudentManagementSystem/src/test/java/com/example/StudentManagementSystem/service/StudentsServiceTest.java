package com.example.StudentManagementSystem.service;

import com.example.StudentManagementSystem.model.Student;
import com.example.StudentManagementSystem.repository.StudentsDal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * StudentsServiceTest
 *
 * <p>Unit tests for {@link StudentsService}. The Spring context is NOT loaded;
 * all dependencies are replaced with Mockito mocks, making these tests fast and
 * isolated from the database.
 *
 * <p>Testing strategy:
 * <ul>
 *   <li>{@code @ExtendWith(MockitoExtension.class)} – activates Mockito annotations
 *       without needing {@code MockitoAnnotations.openMocks(this)}.</li>
 *   <li>{@code @Mock StudentsDal} – creates a mock of the repository layer so no
 *       real database call is ever made.</li>
 *   <li>{@code @InjectMocks StudentsService} – instantiates the service and injects
 *       the mock repository via constructor injection.</li>
 * </ul>
 */
@ExtendWith(MockitoExtension.class)
class StudentsServiceTest {

    // ── Mocks ────────────────────────────────────────────────────────────────

    /** Mocked data access layer – no real DB calls are made. */
    @Mock
    private StudentsDal studentsDal;

    // ── System Under Test ────────────────────────────────────────────────────

    /** The service being tested, with the mock injected automatically. */
    @InjectMocks
    private StudentsService studentsService;

    // ── Test Fixtures ────────────────────────────────────────────────────────

    /** Reusable sample student created before every test method. */
    private Student student;

    /**
     * Initialises a sample {@link Student} before each test.
     * Centralising test data here avoids repetition across test methods.
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

    // ── increementService ────────────────────────────────────────────────────

    /**
     * Verifies that {@link StudentsService#increementService(int)} returns
     * the input value plus 1 for a positive integer.
     */
    @Test
    @DisplayName("increementService: should return num + 1 for positive input")
    void increementService_ShouldReturnIncrementedValue() {

        int result = studentsService.increementService(10);

        assertThat(result).isEqualTo(11);
    }

    /**
     * Verifies that {@link StudentsService#increementService(int)} correctly
     * increments zero to 1.
     */
    @Test
    @DisplayName("increementService: should return 1 when input is 0")
    void increementService_ShouldReturnOneForZeroInput() {

        int result = studentsService.increementService(0);

        assertThat(result).isEqualTo(1);
    }

    /**
     * Verifies that {@link StudentsService#increementService(int)} correctly
     * increments a negative value.
     */
    @Test
    @DisplayName("increementService: should handle negative input")
    void increementService_ShouldHandleNegativeInput() {

        int result = studentsService.increementService(-5);

        assertThat(result).isEqualTo(-4);
    }

    // ── addStudent ───────────────────────────────────────────────────────────

    /**
     * Verifies that {@link StudentsService#addStudent(Student)} delegates to
     * {@link StudentsDal#save(Object)} and returns the saved student.
     */
    @Test
    @DisplayName("addStudent: should save student and return saved entity")
    void addStudent_ShouldSaveAndReturnStudent() {

        when(studentsDal.save(student)).thenReturn(student);

        Student saved = studentsService.addStudent(student);

        assertThat(saved).isNotNull();
        assertThat(saved.getSid()).isEqualTo(1);
        assertThat(saved.getName()).isEqualTo("John Doe");
        verify(studentsDal).save(student);
    }

    /**
     * Verifies that the returned student object contains all expected field values
     * after a successful save.
     */
    @Test
    @DisplayName("addStudent: should return student with all correct field values")
    void addStudent_ShouldReturnStudentWithCorrectFields() {

        when(studentsDal.save(student)).thenReturn(student);

        Student result = studentsService.addStudent(student);

        assertThat(result.getAge()).isEqualTo(20);
        assertThat(result.getCourse()).isEqualTo("Computer Science");
        assertThat(result.getEmailId()).isEqualTo("john.doe@example.com");
        assertThat(result.getContact()).isEqualTo("9999999999");
    }

    // ── deleteStudent ────────────────────────────────────────────────────────

    /**
     * Verifies that {@link StudentsService#deleteStudent(int)} returns {@code true}
     * and calls {@link StudentsDal#deleteById(Object)} when the student exists.
     */
    @Test
    @DisplayName("deleteStudent: should return true and call deleteById when student exists")
    void deleteStudent_ShouldReturnTrueWhenStudentExists() {

        when(studentsDal.existsById(1)).thenReturn(true);

        boolean result = studentsService.deleteStudent(1);

        assertThat(result).isTrue();
        verify(studentsDal).deleteById(1);
    }

    /**
     * Verifies that {@link StudentsService#deleteStudent(int)} returns {@code false}
     * and does NOT call {@link StudentsDal#deleteById(Object)} when the student
     * does not exist.
     */
    @Test
    @DisplayName("deleteStudent: should return false and skip deleteById when student not found")
    void deleteStudent_ShouldReturnFalseWhenStudentNotFound() {

        when(studentsDal.existsById(99)).thenReturn(false);

        boolean result = studentsService.deleteStudent(99);

        assertThat(result).isFalse();
        verify(studentsDal, never()).deleteById(99);
    }

    // ── getStudents ──────────────────────────────────────────────────────────

    /**
     * Verifies that {@link StudentsService#getStudents()} delegates to
     * {@link StudentsDal#findAll()} and returns all students.
     */
    @Test
    @DisplayName("getStudents: should return list of all students from repository")
    void getStudents_ShouldReturnAllStudents() {

        when(studentsDal.findAll()).thenReturn(List.of(student));

        List<Student> students = studentsService.getStudents();

        assertThat(students).hasSize(1);
        assertThat(students.get(0).getName()).isEqualTo("John Doe");
        verify(studentsDal).findAll();
    }

    /**
     * Verifies that {@link StudentsService#getStudents()} returns an empty list
     * when no students are present in the repository.
     */
    @Test
    @DisplayName("getStudents: should return empty list when no students exist")
    void getStudents_ShouldReturnEmptyListWhenNoStudents() {

        when(studentsDal.findAll()).thenReturn(List.of());

        List<Student> students = studentsService.getStudents();

        assertThat(students).isEmpty();
    }
}
