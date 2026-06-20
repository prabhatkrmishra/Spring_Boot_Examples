package EdTech.Course.service;

import EdTech.Course.dto.*;
import EdTech.Course.feign.PaymentService;
import EdTech.Course.feign.UserService;
import EdTech.Course.model.Course;
import EdTech.Course.model.CourseMaterial;
import EdTech.Course.model.Enrollment;
import EdTech.Course.model.User;
import EdTech.Course.repository.CourseRepository;
import EdTech.Course.repository.EnrollmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserService userService;
    private final PaymentService paymentService;

    public CourseService(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository, UserService userService, PaymentService paymentService) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.userService = userService;
        this.paymentService = paymentService;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        return courseOptional.orElse(null);
    }

    public void createCourse(CourseDto courseDto) {
        Course course = new Course();
        course.setAmount(courseDto.getAmount());
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setInstructor(courseDto.getInstructor());
        for (CourseMaterial courseMaterial : courseDto.getCourseMaterial()) {
            courseMaterial.setCourse(course);
        }
        for (Enrollment enrollment : courseDto.getEnrollments()) {
            enrollment.setCourse(course);
        }
        course.setCourseMaterial(courseDto.getCourseMaterial());
        course.setEnrollment(courseDto.getEnrollments());
        courseRepository.save(course);
    }

    public void updateCourse(Long id, CourseDto updatedCourseDto) {
        Course existingCourse = getCourseById(id);
        if (existingCourse != null) {
            existingCourse.setName(updatedCourseDto.getName());
            existingCourse.setDescription(updatedCourseDto.getDescription());
            existingCourse.setInstructor(updatedCourseDto.getInstructor());
            existingCourse.setAmount(updatedCourseDto.getAmount());
            for (CourseMaterial courseMaterial : updatedCourseDto.getCourseMaterial()) {
                courseMaterial.setCourse(existingCourse);
            }
            for (Enrollment enrollment : updatedCourseDto.getEnrollments()) {
                enrollment.setCourse(existingCourse);
            }
            courseRepository.save(existingCourse);
        } else {
            throw new RuntimeException("Course do not exist");
        }
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Course getCourseByName(String name) {
        return courseRepository.findByName(name);
    }

    public Course getCourseByInstructor(String instructor) {
        return courseRepository.findByInstructor(instructor);
    }

    public List<CourseMaterial> getCourseMaterialByCourseId(Long id) {
        return courseRepository.findById(id).orElseThrow().getCourseMaterial();
    }

    public ResponseMessage createEnrollmentForCourse(Long courseId, Long userId) {

        // Step 1: Login via UserService Feign client to get JWT token
        LoginRequest loginRequest = new LoginRequest("john@gmail.com", "john");
        ResponseEntity<LoginResponse> loginResponse = userService.login(loginRequest);
        if (loginResponse.getBody() == null) {
            throw new RuntimeException("Failed to obtain JWT token");
        }
        String jwtToken = "Bearer " + loginResponse.getBody().getToken();

        // Step 2: Verify user exists using JWT token
        User user = userService.getUserById(jwtToken, userId);
        if (user == null) {
            throw new RuntimeException("User does not exist");
        }

        // Step 3: Fetch course and create enrollment
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setUserId(user.getId());
        enrollment.setCourse(course);
        enrollmentRepository.save(enrollment);

        // Step 4: Trigger payment via PaymentService Feign client
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setUserId(user.getId());
        paymentRequest.setCourseId(courseId);
        paymentRequest.setAmount(course.getAmount());
        paymentRequest.setDate(LocalDate.now().toString());
        paymentService.createPayment(paymentRequest);

        return new ResponseMessage("Student Enrolled Successfully");
    }
}
