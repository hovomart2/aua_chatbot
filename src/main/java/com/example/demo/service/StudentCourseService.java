package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.StudentCourse;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentCourseRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

@Service
public class StudentCourseService {
    private final StudentCourseRepository studentCourseRepository;
    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    @Autowired
    public StudentCourseService(StudentCourseRepository studentCourseRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentCourseRepository = studentCourseRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public void save(MultipartFile file) {
        String line = "";
        String splitBy = ";";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                StudentCourse studentCourse = new StudentCourse();
                Optional<Student> oStudent = studentRepository.findByAuaId(data[0]);
                if (oStudent.isEmpty()) {
                    continue;
                }
                Student student = oStudent.get();
                studentCourse.setStudent(student);
                Optional<Course> oCourse = courseRepository.findByCourseCode(data[1]);
                if (oCourse.isEmpty()) {
                    continue;
                }
                Course course = oCourse.get();
                studentCourse.setCourse(course);
                studentCourse.setGrade(data[2]);
                studentCourseRepository.save(studentCourse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

