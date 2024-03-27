package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;
import java.util.List;

@RestController
@RequestMapping(value = "/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping()
    public ResponseEntity<?> saveCourse(@RequestBody MultipartFile file) {
        String message = "";

        if ("text/csv".equals(file.getContentType())) {
            try {
                courseService.saveCourse(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @PostMapping("/prerequisite")
    public ResponseEntity<?> savePrerequisite(@RequestBody MultipartFile file) {
        String message = "";

        if ("text/csv".equals(file.getContentType())) {
            try {
                courseService.savePrerequisite(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @GetMapping("{courseName}/prerequisite")
    public ResponseEntity<?> getPrerequisite(@PathVariable String courseName){
        List<Course> courseList;
        try {
            courseList = courseService.getPrerequisiteByName(courseName);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not find the course!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(courseList);
    }

}
