package com.example.studentservice.service;
import java.util.Collections;
import com.example.studentservice.model.Student;
import com.example.studentservice.repository.StudentRepository;
import com.example.studentservice.model.Course;
import com.example.studentservice.service.clients.CourseServiceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseServiceClient courseServiceClient;
    private final RestTemplate restTemplate;

    public StudentService(StudentRepository studentRepository, CourseServiceClient courseServiceClient, RestTemplate restTemplate) {
        this.studentRepository = studentRepository;
        this.courseServiceClient = courseServiceClient;
        this.restTemplate = restTemplate;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        if (studentRepository.existsById(id)) {
            updatedStudent.setId(id);
            return studentRepository.save(updatedStudent);
        }
        throw new RuntimeException("Student not found");
    }

    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    // Get Courses from Course Service with Circuit Breaker protection
    @CircuitBreaker(name = "courseServiceCircuitBreaker", fallbackMethod = "getCoursesFallback")
    public List<Course> getCoursesFromCourseService() {
        return courseServiceClient.getAllCourses();
    }

    // Fallback method (called when circuit is open or Course service is down)
    public List<Course> getCoursesFallback(Throwable t) {
        System.out.println("Fallback triggered: " + t.getMessage());
        Course fallbackCourse = new Course();
        fallbackCourse.setId(0L);
        fallbackCourse.setName("Fallback Course");
        fallbackCourse.setDescription("Unavailable");
        return Collections.singletonList(fallbackCourse);


    }
}
