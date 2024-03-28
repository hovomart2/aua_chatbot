package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void saveCourse(MultipartFile multipartFile) {

        String line = "";
        String splitBy = "\\$";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(), "UTF-8"))){
            String row = br.readLine();
            while (row != null) {
                line = row;
                line = line.replaceAll(splitBy, splitBy + " ");
                String[] data = line.split(splitBy);
                row = br.readLine();
                while (row != null && row.replaceAll(splitBy, splitBy + " ").split(splitBy).length != 4) {
                    data[3] += row;
                    row = br.readLine();
                }
                Course course;
                int credits;
                try {
                    credits = Integer.parseInt(data[2].substring(1));
                }catch (NumberFormatException e){
                    course = new Course(data[0], data[1].substring(1), data[3].substring(1));
                    courseRepository.save(course);
                    continue;
                }
                course = new Course(data[0], data[1].substring(1), credits, data[3].substring(1));
                courseRepository.save(course);
                if (row == null) {
                    break;
                }
            }
            System.out.println("good");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void savePrerequisite(MultipartFile multipartFile) {
        String line = "";
        String splitBy = "\\$";
        String splitPrBy = "&";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(), "UTF-8"))){
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                if (data.length == 1){
                    continue;
                }
                Optional<Course> oCourse = courseRepository.findByCourseCode(data[0]);
                if(oCourse.isEmpty()){
                    continue;
                }
                Course course = oCourse.get();
                String[] prerequisiteCodes = data[1].split(splitPrBy);
                Set<Course> prerequisites = new HashSet<>();
                for (String prerequisiteCode : prerequisiteCodes){
                    prerequisites.add(courseRepository.findByCourseCode(prerequisiteCode).get());
                }
                course.setPrerequisites(prerequisites);
                courseRepository.save(course);
            }
            System.out.println("good");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Course> getPrerequisiteByName(String courseName){
        Optional<Course> oCourse = courseRepository.findByCourseName(courseName);
        if(oCourse.isEmpty()){
            throw new EntityNotFoundException();
        }
        Course course = oCourse.get();
        return course.getPrerequisites().stream().toList();
    }
}
