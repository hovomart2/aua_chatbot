package com.example.demo.controller;

import com.example.demo.service.SemesterCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/semester-course")
public class SemesterCourseController {
    private final SemesterCourseService semesterCourseService;

    @Autowired
    public SemesterCourseController(SemesterCourseService semesterCourseService) {
        this.semesterCourseService = semesterCourseService;
    }

    @PostMapping()
    public ResponseEntity<?> saveSemesterCourse(@RequestBody MultipartFile file) {
        String message = "";

        if ("text/csv".equals(file.getContentType())) {
            try {
                semesterCourseService.saveSemesterCourse(file);
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
}
