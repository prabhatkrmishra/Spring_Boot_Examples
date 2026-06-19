package com.example.StudentManagementSystem.service;

import com.example.StudentManagementSystem.model.Student;
import com.example.StudentManagementSystem.repository.StudentsDal;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * StudentsService
 *
 * <p>Service layer that encapsulates all business logic for student operations.
 * Annotated with {@code @Service} so Spring registers it as a managed bean and
 * it can be injected into the controller layer.
 *
 * <p>Constructor injection is used (rather than field injection) to keep the
 * dependency explicit and testable without a Spring context (plain Mockito tests).
 *
 * <p>Available operations:
 * <ul>
 *   <li>{@link #increementService(int)}   – increments a number by 1</li>
 *   <li>{@link #addStudent(Student)}      – persists a new student record</li>
 *   <li>{@link #deleteStudent(int)}       – removes a student record by ID</li>
 *   <li>{@link #getStudents()}            – retrieves all student records</li>
 * </ul>
 */
@Service
public class StudentsService {

    /** Data Access Layer injected via constructor. Marked final for immutability. */
    private final StudentsDal studentsDal;

    /**
     * Constructs a {@code StudentsService} with the provided {@link StudentsDal}.
     *
     * @param studentsDal the repository used to perform database operations
     */
    public StudentsService(StudentsDal studentsDal) {
        this.studentsDal = studentsDal;
    }

    /**
     * Increments the given integer by 1.
     *
     * <p>Simple utility method included per the assignment specification.
     *
     * @param num the input integer value
     * @return the input value incremented by 1
     */
    public int increementService(int num) {
        return num + 1;
    }

    /**
     * Persists a new {@link Student} record to the database.
     *
     * <p>Delegates to {@link StudentsDal#save(Object)}, which performs an
     * SQL {@code INSERT} for a new entity or an {@code UPDATE} for an existing one.
     *
     * @param student the student entity to save
     * @return the saved {@link Student} instance (with any database-generated values)
     */
    public Student addStudent(Student student) {
        return studentsDal.save(student);
    }

    /**
     * Deletes the {@link Student} with the specified ID from the database.
     *
     * <p>First checks whether the student exists via
     * {@link StudentsDal#existsById(Object)}. If not found, returns {@code false}
     * without attempting a delete. If found, deletes the record and returns
     * {@code true}.
     *
     * @param sid the student ID (primary key) of the record to delete
     * @return {@code true} if the student was found and deleted;
     *         {@code false} if no student with the given ID exists
     */
    public boolean deleteStudent(int sid) {

        if (!studentsDal.existsById(sid)) {
            return false;
        }

        studentsDal.deleteById(sid);
        return true;
    }

    /**
     * Retrieves all {@link Student} records from the database.
     *
     * <p>Delegates to {@link StudentsDal#findAll()}, which performs a
     * full-table {@code SELECT} query.
     *
     * @return a {@link List} of all students; an empty list if none exist
     */
    public List<Student> getStudents() {
        return studentsDal.findAll();
    }
}
