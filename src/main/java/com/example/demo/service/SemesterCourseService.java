package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.SemesterCourse;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.SemesterCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SemesterCourseService {
    private final SemesterCourseRepository semesterCourseRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public SemesterCourseService(SemesterCourseRepository semesterCourseRepository, CourseRepository courseRepository) {
        this.semesterCourseRepository = semesterCourseRepository;
        this.courseRepository = courseRepository;
    }

    public void saveSemesterCourse(MultipartFile multipartFile) {
        String line = "";
        String splitBy = "\\$";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(), "UTF-8"))) {
            while ((line = br.readLine()) != null) {
                line = line.replaceAll(splitBy, splitBy + " ");
                String[] data = line.split(splitBy);
                SemesterCourse semesterCourse = new SemesterCourse();
                Optional<Course> oCourse = courseRepository.findByCourseCode(data[0]);
                if (oCourse.isEmpty()) {
                    continue;
                }
                semesterCourse.setCourse(oCourse.get());
                semesterCourse.setSection(data[1].substring(1));
                semesterCourse.setSession(data[2].substring(1));
                semesterCourse.setCampus(data[3].substring(1));
                semesterCourse.setInstructorName(data[4].substring(1));
                semesterCourse.setTimes(data[5].substring(1));
                semesterCourse.setTakenAndSeats(data[6].substring(1));
                try {
                    semesterCourse.setSpacesWaiting(Integer.parseInt(data[7].substring(1)));
                } catch (NumberFormatException e) {
                    semesterCourse.setSpacesWaiting(0);
                }
                semesterCourse.setLocation(data[8].substring(1));
                semesterCourseRepository.save(semesterCourse);
            }
            System.out.println("good");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
