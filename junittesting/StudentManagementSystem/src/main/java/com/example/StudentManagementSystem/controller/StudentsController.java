package com.example.StudentManagementSystem.controller;

import com.example.StudentManagementSystem.model.Student;
import com.example.StudentManagementSystem.service.StudentsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * StudentsController
 *
 * <p>REST controller that exposes the Student Management API under the
 * base path {@code /students}.
 *
 * <p>Annotations used:
 * <ul>
 *   <li>{@code @RestController}   – combines {@code @Controller} and {@code @ResponseBody};
 *       return values are serialised directly to JSON in the HTTP response body.</li>
 *   <li>{@code @RequestMapping}   – sets the common path prefix {@code /students}
 *       for all endpoints in this class.</li>
 *   <li>{@code @AllArgsConstructor} – Lombok generates an all-args constructor so
 *       Spring can inject {@link StudentsService} without an explicit {@code @Autowired}.</li>
 * </ul>
 *
 * <p>Available endpoints:
 * <ul>
 *   <li>GET    /students/getStudents         – retrieve all students</li>
 *   <li>POST   /students/addStudent          – add a new student</li>
 *   <li>DELETE /students/deleteStudent/{sid} – delete a student by ID</li>
 * </ul>
 */
@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentsController {

    /** Service layer injected via the Lombok-generated all-args constructor. */
    private final StudentsService studentsService;

    /**
     * GET /students/getStudents
     *
     * <p>Retrieves a list of all students currently stored in the database.
     *
     * @return a {@link List} of {@link Student} objects serialised as a JSON array;
     *         returns an empty array if no students exist
     */
    @GetMapping("/getStudents")
    public List<Student> getStudents() {
        return studentsService.getStudents();
    }

    /**
     * POST /students/addStudent
     *
     * <p>Persists a new student record. The request body must contain a valid
     * JSON representation of a {@link Student}.
     *
     * <p>Example request body:
     * <pre>{@code
     * {
     *   "sid": 1,
     *   "name": "Alice",
     *   "age": 21,
     *   "course": "Computer Science",
     *   "address": "123 Main St",
     *   "emailId": "alice@example.com",
     *   "contact": "9876543210"
     * }
     * }</pre>
     *
     * @param student the {@link Student} entity deserialised from the JSON request body
     * @return the saved {@link Student} object (including any database-assigned values)
     */
    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student) {
        return studentsService.addStudent(student);
    }

    /**
     * DELETE /students/deleteStudent/{sid}
     *
     * <p>Deletes the student identified by the given {@code sid} path variable.
     * Returns a descriptive plain-text message indicating the outcome.
     *
     * @param sid the student ID extracted from the URL path
     * @return {@code "Deleted Student SID: <sid>"} on success, or
     *         {@code "Student does not exist!"} if no matching record is found
     */
    @DeleteMapping("/deleteStudent/{sid}")
    public String deleteStudent(@PathVariable int sid) {

        boolean deleted = studentsService.deleteStudent(sid);

        if (!deleted) {
            return "Student does not exist!";
        }

        return "Deleted Student SID: " + sid;
    }
}
