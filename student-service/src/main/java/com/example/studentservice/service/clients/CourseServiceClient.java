package com.example.studentservice.service.clients;

import com.example.studentservice.model.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "course-service")
public interface CourseServiceClient {
    
    @GetMapping("/courses")
    List<Course> getAllCourses();
}
