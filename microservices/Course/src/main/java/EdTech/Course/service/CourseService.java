package EdTech.Course.service;

import EdTech.Course.dto.CourseDto;
import EdTech.Course.dto.Payment;
import EdTech.Course.dto.ResponseMessage;
import EdTech.Course.model.Course;
import EdTech.Course.model.CourseMaterial;
import EdTech.Course.model.Enrollment;
import EdTech.Course.repository.CourseRepository;
import EdTech.Course.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    private final RestTemplate restTemplate;
    public CourseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
        for(CourseMaterial courseMaterial : courseDto.getCourseMaterial()){
            courseMaterial.setCourse(course);
        }
        for(Enrollment enrollment : courseDto.getEnrollments()){
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
            for(CourseMaterial courseMaterial : updatedCourseDto.getCourseMaterial()){
                courseMaterial.setCourse(existingCourse);
            }
            for(Enrollment enrollment : updatedCourseDto.getEnrollments()){
                enrollment.setCourse(existingCourse);
            }
            courseRepository.save(existingCourse);
        }
        else{
            throw new RuntimeException("Course do not exist");
        }
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Course getCourseByName(String name) {
        return courseRepository.findByName(name);
    }

    public Course getCourseByInstructor(String instructor){
        return courseRepository.findByInstructor(instructor);
    }

    public List<CourseMaterial> getCourseMaterialByCourseId(Long id){
        return courseRepository.findById(id).orElseThrow().getCourseMaterial();
    }

    public ResponseMessage createEnrollmentForCourse(Long courseId, Long userId) {
        // Step 1: Get JWT token from UserService login
        String loginUrl = "http://user-service/auth/login";
        HttpHeaders loginHeaders = new HttpHeaders();
        loginHeaders.setContentType(MediaType.APPLICATION_JSON);
        String loginBody = "{\"username\":\"john@gmail.com\",\"password\":\"john\"}";
        HttpEntity<String> loginRequest = new HttpEntity<>(loginBody, loginHeaders);
        ResponseEntity<Map> loginResponse = restTemplate.postForEntity(loginUrl, loginRequest, Map.class);
        String jwtToken = (String) loginResponse.getBody().get("token");

        // Step 2: Call UserService to check if user exists, passing JWT token in header
        String userServiceUrl = "http://user-service/users/" + userId;
        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.set("Authorization", "Bearer " + jwtToken);
        HttpEntity<Void> authRequest = new HttpEntity<>(authHeaders);
        ResponseEntity<Object> userResponse = restTemplate.exchange(userServiceUrl, HttpMethod.GET, authRequest, Object.class);

        // Step 3: If user exists, create enrollment
        if (userResponse.getStatusCode().is2xxSuccessful() && userResponse.getBody() != null) {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found"));

            Enrollment enrollment = new Enrollment();
            enrollment.setUserId(userId);
            enrollment.setCourse(course);
            enrollmentRepository.save(enrollment);

            // Step 4: Communicate with PaymentService to generate payment
            Payment payment = new Payment();
            payment.setUserId(userId);
            payment.setCourseId(courseId);
            payment.setAmount(course.getAmount());
            payment.setDate(LocalDate.now().toString());

            String paymentUrl = "http://payment-service/payments";
            HttpHeaders paymentHeaders = new HttpHeaders();
            paymentHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Payment> paymentRequest = new HttpEntity<>(payment, paymentHeaders);
            restTemplate.postForEntity(paymentUrl, paymentRequest, Object.class);

            return new ResponseMessage("Student Enrolled Successfully");
        } else {
            throw new RuntimeException("User does not exist");
        }
    }

}